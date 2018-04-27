package com.fms.controller.order;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fms.db.model.Orders;
import com.fms.service.order.OrdersService;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.util.StringUtils;

@Controller
@RequestMapping("/ordersCtrl")
public class OrdersController {
	Date date=new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private OrdersService os;
	
	@RequestMapping("/listOrdersUrl")
	public String listOrders(Model model) {
		return "fms/order/listOrder";
	}
	
	@RequestMapping("/getOrdersAllByPage")
	@ResponseBody
	public Map<String, Object> getOrdersAllByPage(String productName,String strategyName,String chnName,
			String agentName,String orderStatus,String contactStatus,String beginPreorderCreatetime,
			String endPreorderCreatetime,String beginPreorderCompletetime,String endPreorderCompletetime,String beginPayTime,
			String endPayTime,int page,int rows){
		Orders orders = null;
		try {
			orders = new Orders(StringUtils.isNullByStr(contactStatus), StringUtils.isNullByStr(orderStatus), page, rows, 
					StringUtils.isEmpty(beginPreorderCreatetime) ? null: sdf.parse(beginPreorderCreatetime),
					StringUtils.isEmpty(endPreorderCreatetime) ? null: sdf.parse(endPreorderCreatetime), 
					StringUtils.isEmpty(beginPreorderCompletetime) ? null: sdf.parse(beginPreorderCompletetime), 
					StringUtils.isEmpty(endPreorderCompletetime) ? null: sdf.parse(endPreorderCompletetime), 
					StringUtils.isEmpty(beginPayTime) ? null: sdf.parse(beginPayTime), 
					StringUtils.isEmpty(endPayTime) ? null: sdf.parse(endPayTime), StringUtils.isNullByStr(productName), 
					StringUtils.isNullByStr(strategyName), StringUtils.isNullByStr(chnName), StringUtils.isNullByStr(agentName));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return os.getDatagrid(orders);
		
	}
	
	@RequestMapping("/insertOrders")
	@ResponseBody
	public Map<String,Object> insertOrders(String productId,String olientId,String amount,String preorderCreatetime,String orderStatus,HttpSession session){
		BigDecimal bigDecimal=new BigDecimal(amount);
		DefUser user=(DefUser) session.getAttribute("user");
		Orders o=new Orders();
		o.setRcState("0");
		o.setCreateDate(date);//创建时间
		o.setModifyDate(date);//最后修改时间
		o.setOperComId(1l);
		o.setCreateUserId(user.getUserId());
		o.setUpdateUserId(user.getUserId());
		o.setProductId(productId);
		o.setOlientId(olientId);
		o.setOrderStatus(orderStatus);
		o.setAmount(bigDecimal);
		try {
			o.setPreorderCreatetime(sdf.parse(preorderCreatetime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		o.setOrderStatus(orderStatus);
		Map<String,Object> map=new HashMap<String,Object>();
		if(os.insertOrders(o)==0) {
			map.put("success", false);
			map.put("msg", "添加失败！");
		}else {
			map.put("success", true);
			map.put("msg", "添加成功！");
		}
		return map;
	}
	
	@RequestMapping("/getStateGroupByOrdersState")
	@ResponseBody
	public List<Orders> getStateGroupByOrdersState(){
		return os.getStateGroupByOrdersState();
	}
	
	@RequestMapping("/getStateGroupByContactStatus")
	@ResponseBody
	public List<Orders> getStateGroupByContactStatus(){
		return os.getStateGroupByContactStatus();
	}
}
