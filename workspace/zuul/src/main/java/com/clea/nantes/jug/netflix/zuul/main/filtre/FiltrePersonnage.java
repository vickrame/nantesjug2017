/**
 * 
 */
package com.clea.nantes.jug.netflix.zuul.main.filtre;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

/**
 * @author vickrame
 *
 */
@Component
public class FiltrePersonnage extends ZuulFilter {
	public FiltrePersonnage(){
	}
	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	public Object run() {
        HttpServletRequest request = com.netflix.zuul.context.RequestContext.getCurrentContext().getRequest();
        HttpServletResponse response = com.netflix.zuul.context.RequestContext.getCurrentContext().getResponse();
        System.err.println("REQUEST :: < " + request.getScheme() + " " + request.getLocalAddr() + ":" + request.getLocalPort());
        System.err.println("REQUEST :: < " + request.getMethod() + " " + request.getRequestURI() + " " + request.getProtocol());
        System.err.println("RESPONSE:: > HTTP:" + response.getStatus());
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	public boolean shouldFilter() {
		String path = com.netflix.zuul.context.RequestContext.getCurrentContext().getRequest().getRequestURI();
		System.out.println("Path "+ path);
        return true;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */

}
