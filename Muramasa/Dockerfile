# Sử dụng Java image có sẵn từ Maven
FROM maven:3.8.4-jdk-11

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép tất cả các file từ dự án vào thư mục làm việc
COPY . .

# Chạy lệnh Maven để build dự án
RUN mvn clean install

# Thiết lập lệnh khởi chạy ứng dụng sau khi build xong
CMD ["mvn", "exec:java", "-Dexec.mainClass=main.Main"]