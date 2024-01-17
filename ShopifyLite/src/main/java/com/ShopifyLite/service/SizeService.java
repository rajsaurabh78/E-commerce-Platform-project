package com.ShopifyLite.service;

import java.util.List;

import com.ShopifyLite.model.Size;

public interface SizeService{

	public String addSize(Size size);
	public String deleteSize(Integer sId);
	public List<Size> allSize();
	public String updateSize(Size size);
	
}
