package com.gls.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gls.response.Location;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.City;

@Controller
public class GlsController extends AbstractController {

	@Autowired
	private DatabaseReader databaseReader;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@RequestMapping("/clientip")
	public @ResponseBody
	String findClientIp(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

	@RequestMapping("/ipaddress/{ipAddress}")
	public @ResponseBody
	Location findGeoLocation(@PathVariable String ipAddress) throws UnknownHostException, IOException, GeoIp2Exception {
		City model = databaseReader.city(InetAddress.getByName(ipAddress));
		return dozerBeanMapper.map(model.getLocation(), Location.class);
	}

}
