package com.example.leixiang.demoapplication;

/**
 * Created by lei.xiang on 2017/8/8.
 */

public class RealNameConfig {

    public static void init(int type) {
        if (App_Type.FF_APP == type) {
            Bind_One.init(type);
            Bind_Two.init(type);
            Bind_Three.init(type);
        }
    }

    public final static class Bind_One {
        public static void init(int type) {
            switch (type) {
                case App_Type.FF_APP:
                    Layout = R.layout.activity_main;
                    Style = R.style.AppTheme;
                    Name_Show = false;
                    break;
            }
        }

        public static int Layout = R.layout.activity_main;
        public static int Style = R.style.AppTheme;
        public static boolean Name_Show = false;

    }

    public final static class Bind_Two {
        public static void init(int type) {
            switch (type) {
                case App_Type.FF_APP:
                    Layout = R.layout.activity_main;
                    Style = R.style.AppTheme;
                    break;
            }
        }

        public static int Layout = R.layout.activity_main;
        public static int Style = R.style.AppTheme;
    }

    public final static class Bind_Three {
        public static void init(int type) {
            switch (type) {
                case App_Type.FF_APP:
                    Layout = R.layout.activity_main;
                    Style = R.style.AppTheme;
                    break;
            }
        }

        public static int Layout = R.layout.activity_main;
        public static int Style = R.style.AppTheme;
    }

    public final static class App_Type {
        final static int KQ_APP = 1;
        final static int FF_APP = 2;
    }

}
