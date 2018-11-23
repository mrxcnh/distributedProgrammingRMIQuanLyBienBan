Hướng dẫn cài đặt:
0. Khi clone về phải đổi tên thư mục gốc là QuanLyBienBan
1. Tạo schema (VD: quanlybienban) Import file .sql vào mysql
2. Chỉnh sửa file config.properties ( DBMS_DATABASE(quanlybienban), DBMS_USER, DBMS_PASSWORD, SERVER_IP_ADDRESS(địa chỉ máy chạy server nếu chạy nhiều máy), SERVER_PORT(đổi cổng server cho RMIRegistry nếu port đó đã được dùng bởi tiến trình khác))
3. Chạy QuanLyBienBanServer bằng ide hoặc bằng commandline(không click đúp file jar):
	java -jar "absolute_path_to_QuanLyBienBanServer.jar"
4. Chạy QuanLyBienBanClient bằng ide hoặc bằng commandline(Không click đúp file jar):
	java -jar "absolute_path_to_QuanLyBienBanClient.jar"
5. Đăng nhập bằng username trong bảng users, password mặc định là 1234
