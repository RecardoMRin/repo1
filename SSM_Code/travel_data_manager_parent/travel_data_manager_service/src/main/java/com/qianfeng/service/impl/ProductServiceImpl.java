package com.qianfeng.service.impl;

import com.github.pagehelper.PageHelper;
import com.qianfeng.dao.IProductDao;
import com.qianfeng.domain.Product;
import com.qianfeng.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements IProductService {

    @Resource
    private IProductDao productDao;

    @Override
    public List<Product> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return productDao.findAll();
    }

    @Override
    public void addProduct(Product product) {
        String s = UUID.randomUUID().toString();
        product.setId(s);
        productDao.saveProduct(product);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            productDao.deleteById(id);
        }
    }

    @Override
    public Product findById(String id) {
        Product product=productDao.findById(id);
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }
}
