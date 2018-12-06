package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.model.CommodityPicture;
import com.felix.grouppurchase.service.ICommodityService;
import com.felix.grouppurchase.util.JsonTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Date: 2018/11/27 22:46
 * @Author: huangchuwen
 */
@RestController
@RequestMapping("/c/commodity")
public class CommodityController {

    @Autowired
    ICommodityService commodityService;

    /**
     * @Author: huangchuwen
     * @date: 2018/11/27 22:46
     * @Description: 获取所有商品类型
     * @params: callback
     */
    @RequestMapping(value = "/getCommodityType", method = RequestMethod.GET)
    public String getCommodityType( String callback){
        return commodityService.getAllCommodityType(callback);
    }

    /**
     * @Author: huangchuwen
     * @date: 2018/12/5 10:24
     * @Description: 获取所有商品
     * @params: callback
     */
    @RequestMapping(value = "/getAllCommodity", method = RequestMethod.GET)
    public String getAllCommodity( String callback){
        return commodityService.getAllCommodity(callback);
    }

    /**
    *
    * @Author: fangyong
    * @date: 2018/11/29 14:45
    * @Description: 获取仓库中所有商品信息
    * @params: volumeIds,callback
    */
    @RequestMapping(value = "/getCommodityInfo",method = RequestMethod.GET)
    public String getCommodityInfo(String volumeIds, String callback){
        return commodityService.getAllCommodityInfo(volumeIds.split(","), callback);
    }

    /**
     * @Author fangyong
     * @Description 增加商品到仓库中
     * @Date 2018/12/3 16:26
     * @Param volumeIds,commodityName,commodityNumber,commodityDescription,commodityPrice,callback
     **/
    @RequestMapping(value = "/addCommodityById",method = RequestMethod.GET)
    public String addCommodityById(String[] volumeIds, String commodityName, String commodityNumber,
                                 String commodityDescription, String commodityPrice, String callback){
      return commodityService.addCommodityById(volumeIds,commodityName,commodityNumber,commodityDescription,commodityPrice,callback);
    }

    /**
     * @Author fangyong
     * @Description 根据商品id删除仓库中的商品
     * @Date 2018/12/4 11:00 
     * @Param commodityIds, callback
     **/
    @RequestMapping(value = "/delCommodityById",method = RequestMethod.GET)
    public String delCommodityById(String[] commodityIds, String callback){
        return commodityService.delCommodityById(commodityIds, callback);
    }
    
    /**
     * @Author fangyong
     * @Description 更新商品
     * @Date 2018/12/4 14:51 
     * @Param commodityId,commodityName, commodityNumber,commodityDescription,commodityPrice, callback
     **/
    @RequestMapping(value = "/updateCommodity",method = RequestMethod.GET)
    public String updateCommodityById(String commodityId,String commodityName, String commodityNumber,
                                  String commodityDescription, String commodityPrice, String callback){
       return commodityService.updateCommodityById(commodityId,commodityName, commodityNumber,commodityDescription,commodityPrice, callback);
    }

    /**
     * @Author fangyong
     * @Description 高级搜索--根据名称模糊查询
     * @Date 2018/12/4 15:53 
     * @Param
     **/
    @RequestMapping(value = "/getCommodityByName",method = RequestMethod.GET)
    public String getCommodityByName(String commodityName, String callback){
       return commodityService.getCommodityByName(commodityName, callback);
    }

    /**
     * @Author fangyong
     * @Description 上传商品图片
     * @Date 2018/12/5 8:54
     * @Param
     * @return
     **/
    @RequestMapping(value = "/addCommodityPicture", method = RequestMethod.GET)
    public String addCommodityPicture(@RequestParam("file") MultipartFile file, String callback) throws IOException {
        String url;
        System.out.print("上传文件===" + "\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //加个时间戳，尽量避免文件名称重复
        String path = "E:/fileUpload/" + fileName;
        //创建文件路径
        File dest = new File(path);
        //判断文件是否已经存在
        if (dest.exists()) {
            return "文件已经存在";
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        JsonTransfer s = new JsonTransfer();
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            url = "http://localhost:8080/gpsys/commodity/addCommodityPicture/" + fileName;//本地运行项目
            int result = commodityService.addCommodityPicture(fileName, path, url, callback);
            String result1 = s.result(1,"上传成功",path,callback);
            return result1;

        } catch (IOException e) {
            String result1 = s.result(0,"上传失败","",callback);
            return result1;
        }
    }

    /**
     * @Author fangyong
     * @Description 获取商品图片
     * @Date 2018/12/5 10:57
     * @Param
     * @return
     **/
    @RequestMapping(value = "/getCommodityPicture", method = RequestMethod.GET)
    public List<CommodityPicture> getCommodityPicture(String callback){
        List<CommodityPicture> pictureList = commodityService.getCommodityPicture(callback);
        return pictureList;
    }

}
