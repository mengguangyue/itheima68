package cn.e3.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.manager.service.ItemService;
import cn.e3.mapper.TbItemMapper;
import cn.e3.pojo.TbItem;
import cn.e3.pojo.TbItemExample;
import cn.e3.utils.DatagridPagebean;
@Service
public class ItemServiceImpl implements ItemService {
	
	//注入商品mapper接口代理对象
	@Autowired
	private TbItemMapper itemMapper;

	/**
	 * 需求:根据id查询商品数据
	 */
	public TbItem findItemByID(Long itemId) {
		// 根据主键查询商品数据
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}
	/*
	 * 需求:分页查询商品列表
	 * 参数:Integer page,Integer rows  ---->EasyUI自动封装的参数
	 * 返回值:DatagridPagebean
	 * 服务是否发布?--->方法有没有注册到zookeeper注册中心中
	 * 一个接口发送一个服务---->这个方法就是在这个服务中---->所以不需要发布
	 * 一个类--->发布一次服务就OK
	 */
	@Override
	public DatagridPagebean findItemListByPage(Integer page, Integer rows) {
		// 创建example对象
		TbItemExample example = new TbItemExample();
		//在执行之前,设置分页
		PageHelper.startPage(page, rows);
		//执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建pageInfo对象,分页插件pageInfo封装类所有的分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		//创建分页包装类对象
		DatagridPagebean pagebean = new DatagridPagebean();
		//设置总记录
		pagebean.setRows(list);
		//获取总记录
		pagebean.setTotal(pageInfo.getTotal());
		//返回分页对象
		return pagebean;
		
	}

}
