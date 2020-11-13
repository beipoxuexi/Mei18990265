package com.example.myapplication6.bean;

import com.example.myapplication6.R;

import java.util.ArrayList;

public class GoodsInfo {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号

    public GoodsInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        desc = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
    }

    // 声明一个手机商品的名称数组
    private static String[] mNameArray = {
            "兰蔻新精华肌底液", "兰蔻眼部精华肌底液", "兰蔻肌底精华眼霜", "兰蔻肌底修护舒润精华液", "兰蔻新立体塑颜紧致眼霜", "兰蔻菁纯臻颜眼霜",
            "兰蔻清滢柔肤水","兰蔻塑颜修护凝露水+面霜","兰蔻水份缘舒缓柔肤啫喱","兰蔻菁纯臻颜精华乳"
    };
    // 声明一个手机商品的描述数组
    private static String[] mDescArray = {
            "兰蔻小黑瓶面部精华肌底液 30ml 补水保湿修护细腻肌肤淡化细纹",
            "兰蔻大眼精华小黑瓶眼部肌底精华液 20ml 淡化眼纹眼袋舒缓眼周眼霜",
            "兰蔻发光眼霜新肌底精华小黑瓶眼霜 15ml 淡化熬夜黑眼圈焕亮眼周",
            "兰蔻安瓶精华修护舒润精华液 20ml 保湿舒缓弹润透亮",
            "兰蔻塑颜修护眼霜 15ml 提拉紧致保湿滋润眼周淡化细纹",
            "兰蔻新菁纯臻颜焕亮眼霜 20ml 淡化干细纹滋润紧致眼周肌肤抗初老",
            "兰蔻清滢柔肤水 400ml 大粉水爽肤水深层保湿补水",
            "兰蔻塑颜雪花霜50ml+凝露水200ml 亮白紧致",
            "兰蔻水份缘舒缓柔肤啫喱 200ml 清透补水保湿",
            "兰蔻菁纯臻颜精萃精华乳 30ml 紧致饱满保湿滋润护肤"
    };
    // 声明一个手机商品的价格数组
    private static float[] mPriceArray = {760, 680, 530, 690, 580, 1080,435,1475,475,2680};
    // 声明一个手机商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.t01_s, R.drawable.t02_s, R.drawable.t03_s,
            R.drawable.t04_s, R.drawable.t05_s, R.drawable.t06_s,
            R.drawable.t07_s,R.drawable.t08_s,R.drawable.t09_s,
            R.drawable.t10_s
    };
    // 声明一个手机商品的大图数组
    private static int[] mPicArray = {
            R.drawable.t01, R.drawable.t02, R.drawable.t03,
            R.drawable.t04, R.drawable.t05, R.drawable.t06,
            R.drawable.t07,R.drawable.t08,R.drawable.t09,
            R.drawable.t10
    };

    // 获取默认的手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.name = mNameArray[i];
            info.desc = mDescArray[i];
            info.price = mPriceArray[i];
            info.thumb = mThumbArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}

