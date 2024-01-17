package com.ShopifyLite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopifyLite.exception.SizeException;
import com.ShopifyLite.model.Size;
import com.ShopifyLite.repository.SizeRepo;
@Service
public class SizeServiceImpl implements SizeService{

    @Autowired
    private SizeRepo sizeRepo;
	
	@Override
	public String addSize(Size size) {

		sizeRepo.save(size);
		return "Saved";
	}

	@Override
	public String deleteSize(Integer sId) {
		
		Optional<Size> opt=sizeRepo.findById(sId);
		if(opt.isEmpty()) {
			throw new SizeException("Inviled size Id : "+sId);
		}else {
			Size s=opt.get();
			sizeRepo.delete(s);
			return "Deleted";
		}
		
	}

	@Override
	public List<Size> allSize() {
		
		List<Size> slist=sizeRepo.findAll();
		if(slist.size()>0) {
			return slist;
		}else
			throw new SizeException("Empty list.");
		
	}

	@Override
	public String updateSize(Size size) {
		Optional<Size> opt=sizeRepo.findById(size.getSid());
		if(opt.isPresent()) {
			opt.get().setType(size.getType());
			sizeRepo.save(size);
			return "Updated.";
		}else
			throw new SizeException("Invilid size id : "+size.getSid());	
	}

}
