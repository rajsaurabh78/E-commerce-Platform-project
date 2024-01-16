package com.ShopifyLite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopifyLite.model.Size;
import com.ShopifyLite.repository.SizeRepo;
@Service
public class SizeServiceImpl implements SizeService{

    @Autowired
    private SizeRepo sizeRepo;
	
	@Override
	public String addSize(Size size) {
		// TODO Auto-generated method stub
		sizeRepo.save(size);
		return "Saved";
	}

	@Override
	public String deleteSize(Integer sId) {
		// TODO Auto-generated method stub
		return null;
	}

}
