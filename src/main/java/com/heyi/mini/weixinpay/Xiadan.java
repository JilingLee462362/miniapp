package com.heyi.mini.weixinpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heyi.mini.weixinpay.common.Signature;
import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.heyi.mini.weixinpay.common.Configure;
import com.heyi.mini.weixinpay.common.HttpRequest;
import com.heyi.mini.weixinpay.common.RandomStringGenerator;
import com.heyi.mini.weixinpay.model.OrderInfo;
import com.heyi.mini.weixinpay.model.OrderReturnInfo;

/**
 * 统一下单接口
 */
@Slf4j
public  class  Xiadan extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Xiadan() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String openid = request.getParameter("openid");
			OrderInfo order = new OrderInfo();
			order.setAppid(Configure.getAppID());//微信开放平台审核通过的应用APPID
			order.setMch_id(Configure.getMch_id());//微信支付分配的商户号
			order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));//随机字符串，不长于32位。推荐随机数生成算法
			order.setBody("dfdfdf");//商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
			order.setOut_trade_no(RandomStringGenerator.getRandomStringByLength(32));//商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
			order.setTotal_fee(10);//订单总金额，单位为分，详见支付金额
			order.setSpbill_create_ip("123.57.218.54");//支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
			order.setNotify_url("https://www.see-source.com/weixinpay/PayResult");//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
			order.setTrade_type("JSAPI");//支付类型
			order.setOpenid(openid);
			order.setSign_type("MD5");//签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
			//生成签名
			String sign = Signature.getSign(order);
			order.setSign(sign);//签名，详见签名生成算法
			
			
			String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", order);
			System.out.println(result);
			log.info("---------下单返回:"+result);
			XStream xStream = new XStream();
			xStream.alias("xml", OrderReturnInfo.class); 

			OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);
			JSONObject json = new JSONObject();
			json.put("prepay_id", returnInfo.getPrepay_id());
			response.getWriter().append(json.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("-------", e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
