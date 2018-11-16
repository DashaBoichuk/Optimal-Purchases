package com.example.dasha_000.shopping;

import java.util.ArrayList;

/**
 * Created by dasha_000 on 04.04.2018.
 */

public class CartList {
        private static ArrayList<ProductInfo> data;

        public static ArrayList<ProductInfo> getData() {
            return data;
        }

        public static void setData(ArrayList<ProductInfo> data) {
            CartList.data = data;
        }


        public static void addElement(ProductInfo productInfo) {
//переписати

            data = data == null ? new ArrayList<ProductInfo>() : data;
            data.add(productInfo);
        }

        public static void deleteElement(int index) {
            CartList.data.remove(index);
        }
}

