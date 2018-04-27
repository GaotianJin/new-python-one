package com.fms.controller.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fms.db.model.Order;
import com.fms.service.order.OrderService;
import com.sinosoft.util.StringUtils;

@Controller
@RequestMapping("/orderCtrl")
public class OrderController {
	@Autowired
	private OrderService os;
	
	@RequestMapping("/listOrderUrl")
	public String listOrder(Model model) {
		return "fms/order/listOrder";
	}
	
	/**
	 * 查询所有并分页
	 * http://localhost:8080/fms/order/getOrderAllByPage?page=1&rows=10
	 * @param o
	 * @return
	 */
	/*@RequestMapping("/getOrderAllByPage")
	@ResponseBody
	public Map<String,Object> getOrderAllByPage(String o_oProdName,String o_oTactic,String o_oCusName,String oCusAtrbName,String oState,String oPactState,String beginBespeakTime,String endBespeakTime,String beginBeokTime,String endBeokTime,String beginTransferTime,String endTransferTime,int page,int rows){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Order order = null;
		try {
			order = new Order(StringUtils.isNull(o_oProdName), StringUtils.isNull(o_oCusName), StringUtils.isNull(oCusAtrbName),
					StringUtils.isNull(o_oTactic), StringUtils.isNull(oState), StringUtils.isNull(oPactState),
					page, rows, StringUtils.isEmpty(beginBespeakTime) ? null: sdf.parse(beginBespeakTime), StringUtils.isEmpty(endBespeakTime) ? null: sdf.parse(endBespeakTime),
					StringUtils.isEmpty(beginBeokTime) ? null: sdf.parse(beginBeokTime), StringUtils.isEmpty(endBeokTime) ? null: sdf.parse(endBeokTime), 
					StringUtils.isEmpty(beginTransferTime) ? null: sdf.parse(beginTransferTime), StringUtils.isEmpty(endTransferTime) ? null: sdf.parse(endTransferTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return os.getDatagrid(order);
	}*/
}
