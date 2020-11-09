package cn.linter.oasys.user.controller;

import cn.linter.oasys.common.entity.Response;
import cn.linter.oasys.user.entity.User;
import cn.linter.oasys.user.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Api(tags = "用户控制器")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过ID查询单个用户
     *
     * @param id 用户ID
     * @return 单个用户
     */
    @ApiOperation("通过ID查询单个用户")
    @GetMapping("user/{id}")
    public Response<User> queryUser(@PathVariable("id") @ApiParam("用户ID") Long id) {
        return Response.sendSuccess(200, userService.query(id));
    }

    /**
     * 分页查询所有用户
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 用户列表
     */
    @ApiOperation("分页查询所有用户")
    @GetMapping("users")
    public Response<List<User>> listUser(@RequestParam(defaultValue = "1") @ApiParam("页号") int pageNumber,
                                         @RequestParam(defaultValue = "10") @ApiParam("页大小") int pageSize) {
        PageInfo<User> pageInfo = userService.list(pageNumber, pageSize);
        return Response.sendSuccess(200, pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 用户
     */
    @ApiOperation("新增用户")
    @PostMapping("user")
    public Response<User> createUser(@RequestBody @ApiParam("用户") User user) {
        return Response.sendSuccess(201, userService.create(user));
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return 用户
     */
    @ApiOperation("更新用户")
    @PutMapping("user")
    public Response<User> updateUser(@RequestBody @ApiParam("用户") User user) {
        return Response.sendSuccess(200, userService.update(user));
    }

    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    @ApiOperation("通过ID删除用户")
    @DeleteMapping("user")
    public Response<Boolean> deleteUser(@ApiParam("用户ID") Long id) {
        return Response.sendSuccess(200, userService.delete(id));
    }

}