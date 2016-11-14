package trainning.broad.helpers;

public class Constants {

	// Attributes
	public static final String ATTR_EMAIL = "email";
	public static final String ATTR_PASSWORD = "password";
	public final static String ATTR_USER_ID = "user_id";
	public final static String ATTR_USER = "user";
	public final static String ATTR_POST = "post";
	public final static String ATTR_COMMENT_ID = "comment_id";
	public final static String ATTR_TAG_ID = "tag_id";
	public final static String ATTR_POST_ID = "post_id";
	public final static String ATTR_POST_TAG_ID = "post_tag_id";
	public final static String ATTR_USER_NAME = "user_name";
	public final static String ATTR_CREATE_AT = "create_at";
	public final static String ATTR_UPDATE_AT = "update_at";
	public final static String ATTR_CONNTENT = "content";
	public final static String ATTR_POST_NAME = "post_name";
	public final static String ATTR_IS_ROLE = "is_role";
	public final static String ATTR_IS_ACTIVE = "is_active";
	public final static String ATTR_TAG_NAME = "tag_name";
	public final static String POST_USER_TAG = "post_user_tag";
	public final static String USER_COMMENT = "user_comment";
	public final static String POST_NAME = "post_name";
	public final static String TAG = "tag";
	public final static String CONFIRM_PASSWORD = "confirmpassword";

	// Links
	public final static String HOME_PATH = "/";
	public final static String LOGIN_PATH = "/login";
	public final static String REGISTER_PATH = "/register";
	public final static String USER_PROFILE_PATH = "/user/profile";
	public final static String HOMEPAGE_JSP = "/WEB-INF/homepage.jsp";
	public final static String LOGIN_JSP = "/WEB-INF/authentication/login.jsp";
	public final static String REGISTER_JSP = "/WEB-INF/authentication/register.jsp";
	public final static String CONFIRM_PASSWORD_JSP = "/WEB-INF/authentication/active.jsp";
	public final static String POST_DETAIL_JSP = "/WEB-INF/post/details.jsp";
	public final static String POST_ADD_JSP = "/WEB-INF/post/add.jsp";
	public final static String POST_EDIT_JSP = "/WEB-INF/post/edit.jsp";
	public final static String USER_PROFILE_JSP = "/WEB-INF/user/profile.jsp";
	public final static String USER_EDIT_JSP = "/WEB-INF/user/edit.jsp";

	// Messages
	public final static String ERROR = "error";
	public final static String MESSAGE = "message";
	public final static String ERROR_EMAIL_OR_PASSWORD = "申し訳ございません。メールとかパスワードは違いました。";
	public final static String ACCOUNT_HAS_AVALIBLE = "申し訳ございません。入力したメールは既に登録しました.";
	public final static String REGISTER_SUCCESS = "登録成功しましたが。我々お客様のメールに認証リンクを送信しました.";
	public final static String ACTIVE_SUCCESS = "アクティブ成功しました、ありがとうございました。";
	public final static String ERROR_UNKONW = "申し訳ございません。知っていないエラーが実行しました。";
	public final static String ADD_SUCCESS = "投稿を成功しました。";
	public final static String ADD_ERROR = "投稿を失敗しました。";
	public final static String LOGIN_SUCCESS = "お帰りなさい、良い日を";
	public final static String EDIT_SUCCESS = "編集成功しました。";
	public final static String EDIT_ERROR = "編集失敗しました。";
	public final static String DELETE_SUCCESS = "削除成功しました。";
	public final static String CONFIRM_PASSWORD_WRONG = "確認パスワードは違いました";
	public final static String INVALID_DATA = "データが違うそうです";

	// table
	public final static String TABLE_USER = "public.user";
	public final static String TABLE_POST = "post";
	public final static String TABLE_TAG = "tag";
	public final static String TABLE_POSTTAG = "post_tag";
	public final static String TABLE_COMMENT = "comment";

	// Mail attributes
	public final static String FROM_EMAIL = "hoang_van_tuan@mediado.jp";
	public final static String EMAIL_PASSWORD = "khf83957";
	public final static String EMAIL_HOST_NAME = "smtp.googlemail.com";
	public final static int SMTP_PORT = 465;

	// Encode String
	public final static String ENCODE = "test";
	public final static String CODE = "code";

}

