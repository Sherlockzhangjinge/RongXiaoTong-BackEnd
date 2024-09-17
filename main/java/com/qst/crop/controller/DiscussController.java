//package com.qst.crop.controller;
//
//import com.qst.crop.common.Result;
//import com.qst.crop.common.StatusCode;
//import com.qst.crop.entity.Discuss;
//import com.qst.crop.security.service.JwtUserDetailsServiceImpl;
//import com.qst.crop.security.util.JwtTokenUtil;
//import com.qst.crop.service.DiscussService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import com.qst.crop.security.entity.JwtUser;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequestMapping("/knowledge")
//public class DiscussController {
//
//    @Autowired
//    private DiscussService discussService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//    @Autowired
//    private JwtUserDetailsServiceImpl jwtUserDetailsService;
//    @GetMapping("/selectByKnowledge/{knowledgeId}")
//    public Result<List<Discuss>> selectByKnowledge(@PathVariable("knowledgeId") Integer knowledgeId) {
//        List<Discuss> list = discussService.selectByKnowledge(knowledgeId);
//
//        return new Result<>(true, StatusCode.OK,"查询成功",list);
//    }
//    /*   @PostMapping("/addByKnowledge/{knowledgeId}/{content}")
//       public Result<Integer> addByKnowledge(@PathVariable("knowledgeId") Integer knowledgeId, @PathVariable("content") String content) {
//           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//           JwtUser principal = (JwtUser) authentication.getPrincipal();
//          String username = principal.getUsername();
//           Discuss discuss = new Discuss(992, knowledgeId, username, content, LocalDateTime.now());
//           return new Result<>(true, StatusCode.OK, "添加讨论成功", discussService.addDiscuss(discuss));
//       }*/
//    @PostMapping("/addByKnowledge/{knowledgeId}/{content}")
//    public Result<Integer> addByKnowledge(@PathVariable("knowledgeId") Integer knowledgeId,
//                                          @PathVariable("content") String content) {
//
//
//       /* // 直接构造 Discuss 对象，不使用登录验证信息
//        Discuss discuss = new Discuss();
//        discuss.setKnowledgeId(knowledgeId);
//        discuss.setContent(content);
//        discuss.setOwnName("董子兴真帅");  // 这里你可以设置一个默认的用户名，比如 "董子兴真帅"
//        discuss.setCreateTime(LocalDateTime.now());
//        // 保存讨论并返回结果
//        int result = discussService.addDiscuss(discuss);
//        return new Result<>(true, StatusCode.OK, "添加讨论成功", result);
//
//
//        return new Result<>(true, StatusCode.OK, "添加讨论成功", result);*/
//
//
//// 从 Spring Security 上下文中获取当前登录的 JwtUse
//// 获取当前用户的用户名
//        JwtUser principal = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = principal.getUsername();
//
//// 构造 Discuss 对象
//        Discuss discuss = new Discuss();
//        discuss.setKnowledgeId(knowledgeId);
//        discuss.setContent(content);
//        discuss.setOwnName(username);  // 使用当前登录用户的用户名
//        discuss.setCreateTime(LocalDateTime.now());
//
//// 保存讨论并返回结果
//        int result = discussService.addDiscuss(discuss);
//        return new Result<>(true, StatusCode.OK, "添加讨论成功", result);
//    }
//}