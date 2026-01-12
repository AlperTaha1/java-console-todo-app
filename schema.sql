-- Veritabanını oluştur (Eğer yoksa)
CREATE DATABASE IF NOT EXISTS todo_app_db;

-- Veritabanını seç
USE todo_app_db;

-- Tabloyu oluştur (Eğer yoksa)
CREATE TABLE IF NOT EXISTS tasks (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'TODO'
    );

-- (Opsiyonel) Örnek bir veri ekle
INSERT INTO tasks (title, status) VALUES ('Örnek Görev: Java öğren', 'IN_PROGRESS');