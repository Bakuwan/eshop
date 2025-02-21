<details>

<summary> Reflection Modul 1 </summary>

## Reflection 1

Standar kode yang dipelajari di Modul 1 - Coding Standards adalah:

1. Clean Code

    a. Kode dengan Nama yang Bermakna:
Pastikan setiap variabel, fungsi, dan kelas memiliki nama yang jelas dan deskriptif agar kode mudah dipahami.

    b. Fungsi Kecil dan Spesifik:
Buatlah fungsi yang hanya melakukan satu tugas spesifik.

    c. Perbaiki Kode, Bukan Hanya Menambahkan Komentar:
Jangan gunakan komentar untuk memperbaiki kode yang buruk.

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



## Reflection 2

1. Menulis unit test adalah langkah penting untuk memastikan bahwa aplikasi kita berfungsi dengan baik. Idealnya sebuah class memiliki unit test yang cukup untuk setiap method dan skenario yang penting.
   100 % Code coverage tidak berarti bahwa kode kita bebas dari bug, karena mungkin masih ada kasus yang belum dicover atau kesalahan yang tidak terlihat.

2. Jika class Java baru memiliki prosedur setup dan instance variables yang sama, bisa menambah duplikasi yang tidak perlu. Kode yang duplikat bisa membuat kode lebih susah untuk dimaintain. Saran perbaikan saya adalah untuk menghindari duplikasi kode agar kode lebih bersih.
</details>

<details>
<summary> Reflection Modul 2</summary>

## Code quality issues dan cara perbaikannya

Pada kode saya isu yang ditemukan adalah penggunaan public identifier pada interface "ProductService.java". Startegi saya dalam memperbaikinya adalah dengan menghapus identifier public pada semua method interface tersebut.

PMD juga menemukan beberapa warning lain yakni `This utility class has a non-private constructor: src/main/java/id/ac/ui/cs/advprog/eshop/EshopApplication.java#L7`. Akan tetapi warning tersebut tidak saya fix karena class tersebut sebenarnya bukan utility class, saya menggunakan @SpringBootTest untuk beberapa unit test saya yang memerlukan instansiasi class `EshopApplication`.

Ada satu lagi warning yang tidak saya fix yakni `Unused import 'org.springframework.web.bind.annotation.*': src/main/java/id/ac/ui/cs/advprog/eshop/controller/ProductController.java#L8`. PMD beranggapan bahwa import tersebut tidak terpakai walaupun sebenarnya import tersebut saya pakai untuk membuat annotation mapping request HTTP.

## CI/CD workflows

Implementasi workflow saya sudah memenuhi CI/CD. Untuk CI, saya menggunakan github workflow untuk mengautomasi testing setiap kali kode di push ke repository. Untuk CD, saya menggunakan Koyeb yang akan mendeploy aplikasi otomatis setelah kode dipush dan berhasil melewati testing.
</details>