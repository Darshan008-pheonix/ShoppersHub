package com.sph.product.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sph.product.config.MongoConfig;
import com.sph.product.dao.CounterService;
import com.sph.product.dao.ProductDao;
import com.sph.product.entity.Inventory;
import com.sph.product.entity.Pricing;
import com.sph.product.entity.Product;
import com.sph.product.entity.ProductStatus;
import com.sph.product.entity.Variant;
import com.sph.product.entity.VariantStatus;
import com.sph.product.service.IProductService;
import com.sph.product.service.ProductMapper;
import com.sph.util.dto.CheckoutResponseDto;
import com.sph.util.dto.ProductDTO;
import com.sph.util.dto.ResponseDto;
import com.sph.util.service.CommonUtils;

@Service
public class ProductServiceImpln implements IProductService {
	
	 private final ProductDao productDao;
	 
	 @Autowired
	 ProductMapper prodMapper;
	 
	 
	    private final CounterService counterService;
	    public ProductServiceImpln(
	            ProductDao productDao,
	            CounterService counterService) {
	        this.productDao = productDao;
	        this.counterService = counterService;
	    }
	
	String generateProductId() {
		  long seq = counterService.getNextSequence("product");
	      return "SPHP" + String.format("%03d", seq);
	}
	

	@Override
	public ResponseDto<Object> addProduct(ProductDTO prod) {
		Product obj = prodMapper.toEntity(prod);
		obj.setPid(generateProductId());
		
//		System.out.println(prod.getPricing());
//		
		
//	    obj.setOwnerId(prod.getOwnerId());
//	    obj.setCategoryId(prod.getCategoryId());
//	    obj.setName(prod.getName());
//	    obj.setBrand(prod.getBrand());
//	    obj.setManufactureDate(prod.getManufactureDate());
//	    obj.setExpiryDate(prod.getExpiryDate());
//	    obj.setDescription(prod.getDescription());
//	    obj.setPricing(Pricing.builder().currency(prod.getPricing().getCurrency()).discount(prod.getPricing().getDiscount()).finalPrice(prod.getPricing().getFinalPrice()).price(prod.getPricing().getPrice()).build());
//	    obj.setInventory(Inventory.builder().reservedStock(prod.getInventory().getReservedStock()).totalStock(prod.getInventory().getTotalStock()).build());
//	    List<Variant> variants = prod.getVariants().stream().map(ele->Variant.builder()
//	    		.variantId(ele.getVariantId())
//	    		.sku(ele.getSku())
//	    		.price(ele.getPrice())
//	    		.stock(ele.getStock())
//	    		.status(VariantStatus.ACTIVE)
//	    		.attributes(ele.getAttributes())
//	    		.build())
//	    		.toList();
//	    obj.setVariants(variants);
//	    obj.setAttributes(prod.getAttributes());
//	    obj.setImages(prod.getImages());
//
 	    obj.setStatus(ProductStatus.ACTIVE);
        obj.setCreatedAt(LocalDateTime.now());
		ProductDTO productdto =prodMapper.toDto(productDao.insertProduct(obj));
		
		return CommonUtils.prepareResponse("Product Had Added ",productdto , HttpStatus.OK.value());
	}

	@Override
	public ResponseDto<Object> validateProduct(String pid, int qnt) {
		
		System.out.println("I am here..!!");
		
		Product prod = productDao.getProduct(pid);
		if(!ObjectUtils.isEmpty(prod)) {
			
		if(prod.getInventory().getTotalStock()>qnt && prod.getExpiryDate().isAfter(LocalDate.now())) {
			return CommonUtils.prepareResponse("Product  Available ",prodMapper.toDto(prod), HttpStatus.OK.value());
		}
		}
		return null;
	}
	
	

}
