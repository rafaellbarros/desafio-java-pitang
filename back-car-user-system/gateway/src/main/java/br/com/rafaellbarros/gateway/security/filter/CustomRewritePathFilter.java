package br.com.rafaellbarros.gateway.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class CustomRewritePathFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getRequestURI().startsWith("/gateway/back-end/");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String originalRequestPath = ctx.getRequest().getRequestURI();
        String newRequestPath = originalRequestPath.replaceFirst("/gateway/back-end/", "/api/");
        ctx.put("requestURI", newRequestPath);
        return null;
    }
}
