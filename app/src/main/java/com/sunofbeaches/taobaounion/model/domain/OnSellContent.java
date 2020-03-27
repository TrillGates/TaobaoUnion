package com.sunofbeaches.taobaounion.model.domain;

import java.util.List;

public class OnSellContent {


    /**
     * success : true
     * code : 10000
     * message : 获取特惠成功.
     * data : {"tbk_dg_optimus_material_response":{"is_default":"false","result_list":{"map_data":[{"category_id":162103,"category_name":"毛衣","click_url":"//s.click.taobao.com/t?e=m%3D2%26s%3DkJjDwvFIqYxw4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0ECvTuG%2B3p84nQtg3Vl34jFm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QNKB%2BYy4c6YhN3ITMvHwC4IkG2QazC6gqykOrGae4DS5oO2CiNcVz0Ka%2BiLKH5gMOE7VEHsV4LYvLtdhRLMp6nxY4S2k1mJ%2BZU%3D&scm=1007.15023.81719.0&pvid=c562ceff-a680-4c0f-827e-d66b1954ac3d&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;originalFloorId:3756;pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d;app_pvid:59590_11.27.14.1_56272_1575279443807&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001","commission_rate":"12.0","coupon_amount":3,"coupon_click_url":"//uland.taobao.com/coupon/edetail?e=GNMakVevGXwNfLV8niU3R5TgU2jJNKOfU1BGIDN741G0%2Bc5Gzwi6geKSfLX1rbqQLGbJes29FQq2QeBl4W2u73iskVg2QFTJNNrkD%2BFGqh1Kuy%2B0WAVbvPskU%2Fn7vZ%2Fe3KE7Ta9aMuRNo1KEDehNsJ4dcb7gMzr8o%2Be84lu5p3EE%2BdAb1JoOOhYd3odUFuqu%2Bs19raUbf6IC13R2LHOwZq5OaBGcL9GSTA0FRtOwCuw%3D&&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;app_pvid:59590_11.27.14.1_56272_1575279443807;tpp_pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001","coupon_end_time":"1575820799000","coupon_info":"满19.00元减3元","coupon_remain_count":100000,"coupon_share_url":"//uland.taobao.com/coupon/edetail?e=GNMakVevGXwNfLV8niU3R5TgU2jJNKOfU1BGIDN741G0%2Bc5Gzwi6geKSfLX1rbqQLGbJes29FQq2QeBl4W2u73iskVg2QFTJNNrkD%2BFGqh1Kuy%2B0WAVbvPskU%2Fn7vZ%2Fe3KE7Ta9aMuRNo1KEDehNsJ4dcb7gMzr8o%2Be84lu5p3EE%2BdAb1JoOOhYd3odUFuqu%2Bs19raUbf6IC13R2LHOwZq5OaBGcL9GSTA0FRtOwCuw%3D&&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;app_pvid:59590_11.27.14.1_56272_1575279443807;tpp_pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001","coupon_start_fee":"19.0","coupon_start_time":"1575216000000","coupon_total_count":100000,"item_description":"自发热德绒阳离子打底衫加厚保暧","item_id":607196010599,"level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"希丹娜旗舰店","pict_url":"//img.alicdn.com/bao/uploaded/i3/3522656586/O1CN01rpRmRs1yWR3wTsiO3_!!0-item_pic.jpg","seller_id":3522656586,"shop_title":"希丹娜旗舰店","small_images":{"string":["//img.alicdn.com/i4/3522656586/O1CN01L2dyS41yWR3ht9IzQ_!!0-item_pic.jpg","//img.alicdn.com/i4/3522656586/O1CN01eCFXa61yWR3hsir1Y_!!3522656586.jpg","//img.alicdn.com/i4/3522656586/O1CN019BBvKo1yWR3XaN4t9_!!3522656586.jpg","//img.alicdn.com/i3/3522656586/O1CN01176svF1yWR3cE8RY1_!!3522656586.jpg"]},"title":"德绒阳离子打底衫加绒加厚保暖半高领纯色2019年新款百搭自发热","user_type":1,"volume":11527,"zk_final_price":"24.8"}]},"request_id":"9rzhkon6fpqb"}}
     */

    private boolean success;
    private int code;
    private String message;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tbk_dg_optimus_material_response : {"is_default":"false","result_list":{"map_data":[{"category_id":162103,"category_name":"毛衣","click_url":"//s.click.taobao.com/t?e=m%3D2%26s%3DkJjDwvFIqYxw4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0ECvTuG%2B3p84nQtg3Vl34jFm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QNKB%2BYy4c6YhN3ITMvHwC4IkG2QazC6gqykOrGae4DS5oO2CiNcVz0Ka%2BiLKH5gMOE7VEHsV4LYvLtdhRLMp6nxY4S2k1mJ%2BZU%3D&scm=1007.15023.81719.0&pvid=c562ceff-a680-4c0f-827e-d66b1954ac3d&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;originalFloorId:3756;pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d;app_pvid:59590_11.27.14.1_56272_1575279443807&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001","commission_rate":"12.0","coupon_amount":3,"coupon_click_url":"//uland.taobao.com/coupon/edetail?e=GNMakVevGXwNfLV8niU3R5TgU2jJNKOfU1BGIDN741G0%2Bc5Gzwi6geKSfLX1rbqQLGbJes29FQq2QeBl4W2u73iskVg2QFTJNNrkD%2BFGqh1Kuy%2B0WAVbvPskU%2Fn7vZ%2Fe3KE7Ta9aMuRNo1KEDehNsJ4dcb7gMzr8o%2Be84lu5p3EE%2BdAb1JoOOhYd3odUFuqu%2Bs19raUbf6IC13R2LHOwZq5OaBGcL9GSTA0FRtOwCuw%3D&&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;app_pvid:59590_11.27.14.1_56272_1575279443807;tpp_pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001","coupon_end_time":"1575820799000","coupon_info":"满19.00元减3元","coupon_remain_count":100000,"coupon_share_url":"//uland.taobao.com/coupon/edetail?e=GNMakVevGXwNfLV8niU3R5TgU2jJNKOfU1BGIDN741G0%2Bc5Gzwi6geKSfLX1rbqQLGbJes29FQq2QeBl4W2u73iskVg2QFTJNNrkD%2BFGqh1Kuy%2B0WAVbvPskU%2Fn7vZ%2Fe3KE7Ta9aMuRNo1KEDehNsJ4dcb7gMzr8o%2Be84lu5p3EE%2BdAb1JoOOhYd3odUFuqu%2Bs19raUbf6IC13R2LHOwZq5OaBGcL9GSTA0FRtOwCuw%3D&&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;app_pvid:59590_11.27.14.1_56272_1575279443807;tpp_pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001","coupon_start_fee":"19.0","coupon_start_time":"1575216000000","coupon_total_count":100000,"item_description":"自发热德绒阳离子打底衫加厚保暧","item_id":607196010599,"level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"希丹娜旗舰店","pict_url":"//img.alicdn.com/bao/uploaded/i3/3522656586/O1CN01rpRmRs1yWR3wTsiO3_!!0-item_pic.jpg","seller_id":3522656586,"shop_title":"希丹娜旗舰店","small_images":{"string":["//img.alicdn.com/i4/3522656586/O1CN01L2dyS41yWR3ht9IzQ_!!0-item_pic.jpg","//img.alicdn.com/i4/3522656586/O1CN01eCFXa61yWR3hsir1Y_!!3522656586.jpg","//img.alicdn.com/i4/3522656586/O1CN019BBvKo1yWR3XaN4t9_!!3522656586.jpg","//img.alicdn.com/i3/3522656586/O1CN01176svF1yWR3cE8RY1_!!3522656586.jpg"]},"title":"德绒阳离子打底衫加绒加厚保暖半高领纯色2019年新款百搭自发热","user_type":1,"volume":11527,"zk_final_price":"24.8"}]},"request_id":"9rzhkon6fpqb"}
         */

        private TbkDgOptimusMaterialResponseBean tbk_dg_optimus_material_response;

        public TbkDgOptimusMaterialResponseBean getTbk_dg_optimus_material_response() {
            return tbk_dg_optimus_material_response;
        }

        public void setTbk_dg_optimus_material_response(TbkDgOptimusMaterialResponseBean tbk_dg_optimus_material_response) {
            this.tbk_dg_optimus_material_response = tbk_dg_optimus_material_response;
        }

        public static class TbkDgOptimusMaterialResponseBean {
            /**
             * is_default : false
             * result_list : {"map_data":[{"category_id":162103,"category_name":"毛衣","click_url":"//s.click.taobao.com/t?e=m%3D2%26s%3DkJjDwvFIqYxw4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0ECvTuG%2B3p84nQtg3Vl34jFm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QNKB%2BYy4c6YhN3ITMvHwC4IkG2QazC6gqykOrGae4DS5oO2CiNcVz0Ka%2BiLKH5gMOE7VEHsV4LYvLtdhRLMp6nxY4S2k1mJ%2BZU%3D&scm=1007.15023.81719.0&pvid=c562ceff-a680-4c0f-827e-d66b1954ac3d&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;originalFloorId:3756;pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d;app_pvid:59590_11.27.14.1_56272_1575279443807&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001","commission_rate":"12.0","coupon_amount":3,"coupon_click_url":"//uland.taobao.com/coupon/edetail?e=GNMakVevGXwNfLV8niU3R5TgU2jJNKOfU1BGIDN741G0%2Bc5Gzwi6geKSfLX1rbqQLGbJes29FQq2QeBl4W2u73iskVg2QFTJNNrkD%2BFGqh1Kuy%2B0WAVbvPskU%2Fn7vZ%2Fe3KE7Ta9aMuRNo1KEDehNsJ4dcb7gMzr8o%2Be84lu5p3EE%2BdAb1JoOOhYd3odUFuqu%2Bs19raUbf6IC13R2LHOwZq5OaBGcL9GSTA0FRtOwCuw%3D&&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;app_pvid:59590_11.27.14.1_56272_1575279443807;tpp_pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001","coupon_end_time":"1575820799000","coupon_info":"满19.00元减3元","coupon_remain_count":100000,"coupon_share_url":"//uland.taobao.com/coupon/edetail?e=GNMakVevGXwNfLV8niU3R5TgU2jJNKOfU1BGIDN741G0%2Bc5Gzwi6geKSfLX1rbqQLGbJes29FQq2QeBl4W2u73iskVg2QFTJNNrkD%2BFGqh1Kuy%2B0WAVbvPskU%2Fn7vZ%2Fe3KE7Ta9aMuRNo1KEDehNsJ4dcb7gMzr8o%2Be84lu5p3EE%2BdAb1JoOOhYd3odUFuqu%2Bs19raUbf6IC13R2LHOwZq5OaBGcL9GSTA0FRtOwCuw%3D&&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;app_pvid:59590_11.27.14.1_56272_1575279443807;tpp_pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001","coupon_start_fee":"19.0","coupon_start_time":"1575216000000","coupon_total_count":100000,"item_description":"自发热德绒阳离子打底衫加厚保暧","item_id":607196010599,"level_one_category_id":16,"level_one_category_name":"女装/女士精品","nick":"希丹娜旗舰店","pict_url":"//img.alicdn.com/bao/uploaded/i3/3522656586/O1CN01rpRmRs1yWR3wTsiO3_!!0-item_pic.jpg","seller_id":3522656586,"shop_title":"希丹娜旗舰店","small_images":{"string":["//img.alicdn.com/i4/3522656586/O1CN01L2dyS41yWR3ht9IzQ_!!0-item_pic.jpg","//img.alicdn.com/i4/3522656586/O1CN01eCFXa61yWR3hsir1Y_!!3522656586.jpg","//img.alicdn.com/i4/3522656586/O1CN019BBvKo1yWR3XaN4t9_!!3522656586.jpg","//img.alicdn.com/i3/3522656586/O1CN01176svF1yWR3cE8RY1_!!3522656586.jpg"]},"title":"德绒阳离子打底衫加绒加厚保暖半高领纯色2019年新款百搭自发热","user_type":1,"volume":11527,"zk_final_price":"24.8"}]}
             * request_id : 9rzhkon6fpqb
             */

            private String is_default;
            private ResultListBean result_list;
            private String request_id;

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public ResultListBean getResult_list() {
                return result_list;
            }

            public void setResult_list(ResultListBean result_list) {
                this.result_list = result_list;
            }

            public String getRequest_id() {
                return request_id;
            }

            public void setRequest_id(String request_id) {
                this.request_id = request_id;
            }

            public static class ResultListBean {
                private List<MapDataBean> map_data;

                public List<MapDataBean> getMap_data() {
                    return map_data;
                }

                public void setMap_data(List<MapDataBean> map_data) {
                    this.map_data = map_data;
                }

                public static class MapDataBean implements IBaseInfo {
                    /**
                     * category_id : 162103
                     * category_name : 毛衣
                     * click_url : //s.click.taobao.com/t?e=m%3D2%26s%3DkJjDwvFIqYxw4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0ECvTuG%2B3p84nQtg3Vl34jFm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QNKB%2BYy4c6YhN3ITMvHwC4IkG2QazC6gqykOrGae4DS5oO2CiNcVz0Ka%2BiLKH5gMOE7VEHsV4LYvLtdhRLMp6nxY4S2k1mJ%2BZU%3D&scm=1007.15023.81719.0&pvid=c562ceff-a680-4c0f-827e-d66b1954ac3d&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;originalFloorId:3756;pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d;app_pvid:59590_11.27.14.1_56272_1575279443807&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001
                     * commission_rate : 12.0
                     * coupon_amount : 3
                     * coupon_click_url : //uland.taobao.com/coupon/edetail?e=GNMakVevGXwNfLV8niU3R5TgU2jJNKOfU1BGIDN741G0%2Bc5Gzwi6geKSfLX1rbqQLGbJes29FQq2QeBl4W2u73iskVg2QFTJNNrkD%2BFGqh1Kuy%2B0WAVbvPskU%2Fn7vZ%2Fe3KE7Ta9aMuRNo1KEDehNsJ4dcb7gMzr8o%2Be84lu5p3EE%2BdAb1JoOOhYd3odUFuqu%2Bs19raUbf6IC13R2LHOwZq5OaBGcL9GSTA0FRtOwCuw%3D&&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;app_pvid:59590_11.27.14.1_56272_1575279443807;tpp_pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001
                     * coupon_end_time : 1575820799000
                     * coupon_info : 满19.00元减3元
                     * coupon_remain_count : 100000
                     * coupon_share_url : //uland.taobao.com/coupon/edetail?e=GNMakVevGXwNfLV8niU3R5TgU2jJNKOfU1BGIDN741G0%2Bc5Gzwi6geKSfLX1rbqQLGbJes29FQq2QeBl4W2u73iskVg2QFTJNNrkD%2BFGqh1Kuy%2B0WAVbvPskU%2Fn7vZ%2Fe3KE7Ta9aMuRNo1KEDehNsJ4dcb7gMzr8o%2Be84lu5p3EE%2BdAb1JoOOhYd3odUFuqu%2Bs19raUbf6IC13R2LHOwZq5OaBGcL9GSTA0FRtOwCuw%3D&&app_pvid=59590_11.27.14.1_56272_1575279443807&ptl=floorId:3756;app_pvid:59590_11.27.14.1_56272_1575279443807;tpp_pvid:c562ceff-a680-4c0f-827e-d66b1954ac3d&union_lens=lensId%3AOPT%401575279443%400b1b0e01_0da9_16ec5f8bfe7_2ca3%4001
                     * coupon_start_fee : 19.0
                     * coupon_start_time : 1575216000000
                     * coupon_total_count : 100000
                     * item_description : 自发热德绒阳离子打底衫加厚保暧
                     * item_id : 607196010599
                     * level_one_category_id : 16
                     * level_one_category_name : 女装/女士精品
                     * nick : 希丹娜旗舰店
                     * pict_url : //img.alicdn.com/bao/uploaded/i3/3522656586/O1CN01rpRmRs1yWR3wTsiO3_!!0-item_pic.jpg
                     * seller_id : 3522656586
                     * shop_title : 希丹娜旗舰店
                     * small_images : {"string":["//img.alicdn.com/i4/3522656586/O1CN01L2dyS41yWR3ht9IzQ_!!0-item_pic.jpg","//img.alicdn.com/i4/3522656586/O1CN01eCFXa61yWR3hsir1Y_!!3522656586.jpg","//img.alicdn.com/i4/3522656586/O1CN019BBvKo1yWR3XaN4t9_!!3522656586.jpg","//img.alicdn.com/i3/3522656586/O1CN01176svF1yWR3cE8RY1_!!3522656586.jpg"]}
                     * title : 德绒阳离子打底衫加绒加厚保暖半高领纯色2019年新款百搭自发热
                     * user_type : 1
                     * volume : 11527
                     * zk_final_price : 24.8
                     */

                    private int category_id;
                    private String category_name;
                    private String click_url;
                    private String commission_rate;
                    private int coupon_amount;
                    private String coupon_click_url;
                    private String coupon_end_time;
                    private String coupon_info;
                    private int coupon_remain_count;
                    private String coupon_share_url;
                    private String coupon_start_fee;
                    private String coupon_start_time;
                    private int coupon_total_count;
                    private String item_description;
                    private long item_id;
                    private int level_one_category_id;
                    private String level_one_category_name;
                    private String nick;
                    private String pict_url;
                    private long seller_id;
                    private String shop_title;
                    private SmallImagesBean small_images;
                    private String title;
                    private int user_type;
                    private int volume;
                    private String zk_final_price;

                    public int getCategory_id() {
                        return category_id;
                    }

                    public void setCategory_id(int category_id) {
                        this.category_id = category_id;
                    }

                    public String getCategory_name() {
                        return category_name;
                    }

                    public void setCategory_name(String category_name) {
                        this.category_name = category_name;
                    }

                    public String getClick_url() {
                        return click_url;
                    }

                    public void setClick_url(String click_url) {
                        this.click_url = click_url;
                    }

                    public String getCommission_rate() {
                        return commission_rate;
                    }

                    public void setCommission_rate(String commission_rate) {
                        this.commission_rate = commission_rate;
                    }

                    public int getCoupon_amount() {
                        return coupon_amount;
                    }

                    public void setCoupon_amount(int coupon_amount) {
                        this.coupon_amount = coupon_amount;
                    }

                    public String getCoupon_click_url() {
                        return coupon_click_url;
                    }

                    public void setCoupon_click_url(String coupon_click_url) {
                        this.coupon_click_url = coupon_click_url;
                    }

                    public String getCoupon_end_time() {
                        return coupon_end_time;
                    }

                    public void setCoupon_end_time(String coupon_end_time) {
                        this.coupon_end_time = coupon_end_time;
                    }

                    public String getCoupon_info() {
                        return coupon_info;
                    }

                    public void setCoupon_info(String coupon_info) {
                        this.coupon_info = coupon_info;
                    }

                    public int getCoupon_remain_count() {
                        return coupon_remain_count;
                    }

                    public void setCoupon_remain_count(int coupon_remain_count) {
                        this.coupon_remain_count = coupon_remain_count;
                    }

                    public String getCoupon_share_url() {
                        return coupon_share_url;
                    }

                    public void setCoupon_share_url(String coupon_share_url) {
                        this.coupon_share_url = coupon_share_url;
                    }

                    public String getCoupon_start_fee() {
                        return coupon_start_fee;
                    }

                    public void setCoupon_start_fee(String coupon_start_fee) {
                        this.coupon_start_fee = coupon_start_fee;
                    }

                    public String getCoupon_start_time() {
                        return coupon_start_time;
                    }

                    public void setCoupon_start_time(String coupon_start_time) {
                        this.coupon_start_time = coupon_start_time;
                    }

                    public int getCoupon_total_count() {
                        return coupon_total_count;
                    }

                    public void setCoupon_total_count(int coupon_total_count) {
                        this.coupon_total_count = coupon_total_count;
                    }

                    public String getItem_description() {
                        return item_description;
                    }

                    public void setItem_description(String item_description) {
                        this.item_description = item_description;
                    }

                    public long getItem_id() {
                        return item_id;
                    }

                    public void setItem_id(long item_id) {
                        this.item_id = item_id;
                    }

                    public int getLevel_one_category_id() {
                        return level_one_category_id;
                    }

                    public void setLevel_one_category_id(int level_one_category_id) {
                        this.level_one_category_id = level_one_category_id;
                    }

                    public String getLevel_one_category_name() {
                        return level_one_category_name;
                    }

                    public void setLevel_one_category_name(String level_one_category_name) {
                        this.level_one_category_name = level_one_category_name;
                    }

                    public String getNick() {
                        return nick;
                    }

                    public void setNick(String nick) {
                        this.nick = nick;
                    }

                    public String getPict_url() {
                        return pict_url;
                    }

                    public void setPict_url(String pict_url) {
                        this.pict_url = pict_url;
                    }

                    public long getSeller_id() {
                        return seller_id;
                    }

                    public void setSeller_id(long seller_id) {
                        this.seller_id = seller_id;
                    }

                    public String getShop_title() {
                        return shop_title;
                    }

                    public void setShop_title(String shop_title) {
                        this.shop_title = shop_title;
                    }

                    public SmallImagesBean getSmall_images() {
                        return small_images;
                    }

                    public void setSmall_images(SmallImagesBean small_images) {
                        this.small_images = small_images;
                    }

                    @Override
                    public String getCover() {
                        return pict_url;
                    }

                    public String getTitle() {
                        return title;
                    }

                    @Override
                    public String getUrl() {
                        return coupon_click_url == null ? click_url : coupon_click_url;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public int getUser_type() {
                        return user_type;
                    }

                    public void setUser_type(int user_type) {
                        this.user_type = user_type;
                    }

                    public int getVolume() {
                        return volume;
                    }

                    public void setVolume(int volume) {
                        this.volume = volume;
                    }

                    public String getZk_final_price() {
                        return zk_final_price;
                    }

                    public void setZk_final_price(String zk_final_price) {
                        this.zk_final_price = zk_final_price;
                    }

                    public static class SmallImagesBean {
                        private List<String> string;

                        public List<String> getString() {
                            return string;
                        }

                        public void setString(List<String> string) {
                            this.string = string;
                        }
                    }
                }
            }
        }
    }
}
