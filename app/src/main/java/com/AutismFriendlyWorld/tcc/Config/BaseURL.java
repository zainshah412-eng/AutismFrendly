package com.AutismFriendlyWorld.tcc.Config;

/**
 * Created by zain Shah
 * Hixol on 20/08/2020.
 */

public class BaseURL
{

    static final String APP_NAME = "GroceryDeliver";
    public static final String PREFS_NAME = "GroceryLoginPrefs";
    public static final String PREFS_NAME2 = "GroceryLoginPrefs2";
    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_NAME = "user_fullname";
    public static final String KEY_ID = "user_id";
    public static final String KEY_EMAIL = "user_email";
    public static final String KEY_PASSWORD = "user_password";
    public static final String KEY_PHONE_NO = "user_phone";
    public static final String KEY_IMAGE = "user_image";
    public static final String KEY_LATITIDUE = "user_latitude";
    public static final String KEY_LONGITUDE = "user_longitude";
    public static final String KEY_VEHICLE_TYPE = "vehicle_type";
    public static final String KEY_VEHICLE_NUMBER = "vehicle_number";
    public static final String KEY_VEHICLE_MODEL = "vehicle_MODEL";
    public static final String KEY_VEHICLE_COLOR = "vehicle_color";
    public static final String KEY_USER_GCM_CODE = "user_gcm_code";
    public static final String KEY_USER_IOS_TOKEN = "user_ios_token";
    public static final String KEY_USER_STATUS = "user_status";
    public static final String KEY_ORDER_ID = "ORDER_ID";


    public static String BASE_URL = "http://autismfriendlyworld.co.uk/";


    public static String GET_ORDER_URL = BASE_URL+"index.php/api/delivery_boy_order";
    public static String GET_PENDING_ORDER_URL = BASE_URL+"index.php/api/delivery_boy_pending_orders";
    public static String GET_DELIVERD_ORDER_URL = BASE_URL + "index.php/api/delivery_boy_delivered_orders";
    public static String GET_ALL_ORDER_URL = BASE_URL + "index.php/api/all_pending_orders";
    public static String GET_ACCEPTED_ORDER = BASE_URL + "index.php/api/delivery_boy_accepted_orders";
    public static String GET_ACCEPT_ORDER = BASE_URL + "index.php/api/accept_order";
    public static String GET_NOTIFICATION = BASE_URL + "index.php/api/delivery_boy_notifications_list";
    public static String CHNAGE_PASSWORD = BASE_URL + "index.php/api/delivery_boy_change_password";
    public static String UPLOAD_IMAGE = BASE_URL + "index.php/api/delivery_boy_update_profile_pic";


    public static String IMG_BASE_URL = BASE_URL + "public/uploads/";


    public static String LOGIN = BASE_URL+"api/app/users/login";
    public static String RESET_PASSWORD = BASE_URL+"api/app/users/reset-password";
    public static String CITIES = BASE_URL+"api/app/cities";
    public static String INSPRITION = BASE_URL+"api/app/posts/inspiration";
    public static String POPULAR_CITIES = BASE_URL+"api/app/cities/popular";
    public static String CITYBYID = BASE_URL+"api/app/cities/";
    public static String PLACES = BASE_URL+"api/app/places/search";
    public static String PLACESBYID = BASE_URL+"api/app/places/";


    public static String OrderDetail = BASE_URL + "index.php/api/order_details";
    public static String IMG_PRODUCT_URL = BASE_URL + "public/uploads";
    public static String IMG_USER_URL = BASE_URL + "uploads/profile/";


    public static final String urlUpload = BASE_URL+"index.php/api/mark_delivered2";
    public static final String startOrder = BASE_URL+"index.php/api/start_order";
    public static final String updateLocatio = BASE_URL+"index.php/api/update_delivery_boy_location";
    public static final int REQCODE = 100;
    public static final String image = "signature";
    public static final String imageName = "id";

    public static String JSON_RIGISTER_FCM = BASE_URL + "index.php/api/register_delivery_fcm";
    public static String EDIT_PROFILE_URL = BASE_URL + "";

}
