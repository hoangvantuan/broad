package trainning.broad.helpers;

public class Constants {

	// Attributes
	public static final String ATTR_EMAIL = "email";
	public static final String ATTR_PASSWORD = "password";
	public final static String ATTR_USER_ID = "user_id";
	public final static String ATTR_USER = "user";
	public final static String ATTR_COMMENT_ID = "comment_id";
	public final static String ATTR_TAG_ID = "tag_id";
	public final static String ATTR_POST_ID = "post_id";
	public final static String ATTR_POST_TAG_ID = "post_tag_id";

	// Links
	public final static String ROOT_PATH = "/";
	public final static String LOGIN_PATH = "/login";
	public final static String LOGIN_JSP = "/WEB-INF/authentication/login.jsp";
	public final static String REGISTER_JSP = "/WEB-INF/authentication/register.jsp";
	public final static String URI_LOGIN = "/broad/login";
	public final static String URI_LOGOUT = "/broad/logout";
	public final static String URI_REGISTER = "/broad/register";

	// Messages
	public final static String ERROR = "error";
	public final static String MESSAGE = "message";
	public final static String LOGIN_ERROR = "申し訳ございません。エラーがあるそうですね";
	public final static String LOGIN_SUCCESS = "ただいま、良い日を";
	public final static String ACCOUNT_AVALIBLE_ERROR = "申し訳ございません。入力したメールは既に登録しました.";
	public final static String REGISTER_SUCCESS = "登録成功しましたが。我々お客様のメールに認証リンクを送信しました.";

	// table
	public final static String TABLE_USER = "public.user";
	public final static String TABLE_POST = "post";
	public final static String TABLE_TAG = "tag";
	public final static String TABLE_POSTTAG = "posttag";
	public final static String TABLE_COMMENT = "comment";

	// Mail attributes
	public final static String FROM_EMAIL = "hoang_van_tuan@mediado.jp";
	public final static String EMAIL_PASSWORD = "khf83957";
	public final static String EMAIL_HOST_NAME = "smtp.googlemail.com";
	public final static int SMTP_PORT = 465;

}