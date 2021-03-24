package net.trans.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;


import net.trans.common.domain.Payload;
import net.trans.common.properties.RsaKeyProperties;
import net.trans.common.utils.JwtUtils;
import net.trans.pojo.TransUserTable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 	用户身份校验，授权
 *  @Author Cx
 *  @Date 2021-02-03
 *  @Version 1.0
 *
 */
public class JwtVerifyFilter extends BasicAuthenticationFilter {

    private RsaKeyProperties prop;

    public JwtVerifyFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
        super(authenticationManager);
        this.prop = prop;
    }

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {

            //如果携带错误的token或者token为空，则给用户提示请登录！
            chain.doFilter(request, response);
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Map resultMap = new HashMap();
            resultMap.put("code", HttpServletResponse.SC_FORBIDDEN);
            resultMap.put("msg", "认证失败，请重新登录！");
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        } else {
            //如果携带了正确格式的token要先得到token
            String token = header.replace("Bearer ", "");
            //验证token是否正确
            //通过token解析出载荷信息
            Payload<TransUserTable> payload = JwtUtils.getInfoFromToken(token, prop.getPublicKey(), TransUserTable.class);
            TransUserTable user = payload.getUserInfo();
            if(user!=null){

                //认证成功后，调用的是这个带有三个参数的。
                //public UsernamePasswordAuthenticationToken(Object principal, Object credentials,Collection<? extends GrantedAuthority> authorities)
                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user.getUUsername(), null, user.getRoles());

                //获取后，将Authentication写入SecurityContextHolder中供后序使用
                SecurityContextHolder.getContext().setAuthentication(authResult);
                chain.doFilter(request, response);
            }else {
                PrintWriter out = response.getWriter();
                Map resultMap = new HashMap();
                resultMap.put("code", HttpServletResponse.SC_FORBIDDEN);
                resultMap.put("msg", "认证失败，请重新登录！");
                out.write(new ObjectMapper().writeValueAsString(resultMap));
                out.flush();
                out.close();
            }

        }
    }

}
