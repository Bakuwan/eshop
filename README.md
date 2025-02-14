Standar kode yang dipelajari di Modul 1 - Coding Standards adalah:

1. Clean Code

    a. Kode dengan Nama yang Bermakna:
Pastikan setiap variabel, fungsi, dan kelas memiliki nama yang jelas dan deskriptif agar kode mudah dipahami.

    b. Fungsi Kecil dan Spesifik:
Buatlah fungsi yang hanya melakukan satu tugas spesifik.
2. 
    c. Perbaiki Kode, Bukan Hanya Menambahkan Komentar:
Jangan gunakan komentar untuk memperbaiki kode yang buruk.
2. 
    d. Gunakan Abstract Interface:
Manfaatkan abstract interface untuk menyembunyikan implementasi objek. Hal ini meningkatkan fleksibilitas dan memungkinkan penggantian implementasi tanpa mengubah kode yang menggunakannya.



2. Secure Code

    a. Authentication
   
    Verifikasi harus dilakukan dari sisi server untuk mencegah serangan berbahaya. 

    b. Kebijakan password yang kuat

    Password sangatlah penting untuk mencegah akses tidak sah.

    c. Authorization

    Otorisasi memastikan hanya pengguna sah yang berwenang yang dapat mengakses hal tertentu.
    
    d. Session Management

    Sesi pengguna harus dikelola dengan aman untuk mencegah serangan seperti session hijacking.

    e. JSON Web Tokens (JWT)

   JWT digunakan untuk membawa klaim yang berisi informasi peran dan izin pengguna. Namun, penggunaan JWT harus disertai dengan validasi token yang tepat dan pemilihan algoritma yang aman untuk menghindari potensi risiko keamanan.

    f. Input Validation

   Data yang masuk ke dalam sistem harus divalidasi agar sesuai dengan tipe, format, dan rentang yang diharapkan untuk mencegah serangan injeksi dan manipulasi data. 