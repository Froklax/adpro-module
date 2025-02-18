### Deployment
Link Deployment : https://missing-shrew-froklaxorg-d72833a5.koyeb.app/

### Fast Links
- [Refleksi Module 1](#module-1---coding-standards)
- [Refleksi Module 2](#module-2---cicd--devops)

# Module 1 - Coding Standards

## Reflection 1

### Clean Code Principles
- Menggunakan nama-nama yang deskriptif untuk variabel dan _method_ yang dibuat, seperti `updateProduct` dan `deleteProduct`.
- Setiap class memiliki fungsi masing-masing yang terpisah, contohnya `ProductController` yang menangani _request_ `HTTP`, `ProductService` menangani logika bisnis, dan `ProductRepository` menangani penyimpanan data produk.
- Membuat _method_ kecil yang melakukan suatu hal spesifik, contohnya `getProduct` yang fungsinya adalah untuk mengembalikan produk yang sesuai berdasarkan id produk.
- Menggunakan konvensi penamaan `Java`, seperti menggunakan `camelCase` untuk nama variabel dan _method_.

### Secure Coding Practices

- Menerapkan id produk yang susah ditebak dengan memberikan id random untuk setiap produk yang dibuat menggunakan `java.util.UUID`.
- Menggunakan _method_ `HTTP` yang sesuai, seperti `GET` untuk pengambilan dan `POST` untuk operasi yang mengubah `state`.
- Redirect setelah `POST`, seperti menggunakan `redirect:/product/list` untuk menghindari duplikat submisi dari _form_.
- Melakukan sanitasi _input_, form yang dibuat menggunakan `th:field` yang dimiliki `Thymeleaf`.

### Kesalahan Yang Ditemukan
- Melanggar prinsip _clean code_ dengan tidak menggunakan _method_ yang sudah dibuat sebelumnya, _method_ `deleteProduct` membuat `productData.iterator()` baru walaupun sudah ada _method_ `findAll` yang mengembalikan `productData.iterator()`. Saya memperbaikinya dengan mengganti `productData.iterator()` di dalam `deleteProduct` dengan _method_ `findAll` yang sudah dibuat.

## Reflection 2

1. Setelah saya membuat semua _unit test_ yang diminta, saya merasa lebih yakin dengan kebenaran kode saya meski masih perlu dipastikan semua kasus tercover. Menurut saya sendiri, jumlah unit test per kelas sebaiknya menyesuaikan kompleksitas fungsionalitas, dengan fokus pada fungsi utama kode dan _edge cases_. _Code coverage_ sendiri dapat digunakan sebagai metrik, tetapi 100% _coverage_ tidak menjamin kode bebas _error_ karena _code coverage_ hanya mengukur jumlah eksekusi baris bukan kebenaran logika semua skenario yang di tes. _Code coverage_ 90% yang mengecek semua skenario dan _edge cases_ dengan benar bisa lebih baik dari _code coverage_ 100% yang tidak mengecek hal tersebut.


2. Menurut saya sendiri, berdasarkan kode `CreateProductFunctionalTest.java` yang telah dibuat, terdapat potensi duplikasi kode jika saya membuat _functional test suite_ baru dengan setup yang serupa. Beberapa di antaranya adalah inisialisasi variabel `baseUrl`, `serverPort`, dan `endpoint` yang digunakan. Hal ini tentunya mengurangi kebersihan dan kualitas dari kode yang dibuat. Selain itu, untuk memverifikasi jumlah item dalam daftar produk, saya perlu memastikan produk telah dibuat terlebih dahulu. Hal ini sebenarnya sudah dilakukan dalam pengujian sebelumnya. Oleh karena itu, pengujian ini bisa digabungkan dengan test suite yang ada untuk mengurangi redundansi. Metode verifikasi saat ini bergantung pada perhitungan elemen `<td>` dalam `HTML`, yang rentan terhadap perubahan UI. Menurut saya, lebih baik menggunakan pendekatan yang lebih rentan terhadap perubahan, seperti pencarian elemen berdasarkan teks atau _selector_ yang lebih spesifik. Tetapi, penggabungan pengujian ini dapat membuat _method_ menjadi panjang dan sulit dibaca. Saran perbaikan dari saya sendiri, sebaiknya dilakukan pemisahan antara setup data awal dan metode pengujian yang lebih terstruktur. Dengan ini kode akan tetap terstruktur dan lebih fleksibel terhadap perubahan.

# Module 2 - CI/CD & DevOps

### 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

- __Menghapus modifier `public` pada inteface `ProductService`:__

    Saya menemukan penggunaan modifier `public` yang tidak perlu pada method-method di `ProductService` `interface` melalui analisis kode menggunakan pmd. Dalam Java, setiap method pada interface secara otomatis bersifat `public`, sehingga penulisan modifier `public` menjadi redundant. Untuk mengatasi masalah ini, saya menghapus semua modifier `public` pada method-method tersebut, sehingga kode menjadi lebih bersih dan meningkatkan kualitas kode. Setelah melakukan perubahan tersebut, saya kembali menjalankan analisis kode menggunakan pmd untuk memastikan bahwa masalah tersebut sudah terselesaikan. Hasilnya, warning untuk masalah tersebut tidak muncul lagi, yang menunjukkan bahwa perbaikan tersebut berhasil.


- __Menghapus import * dan menggantinya dengan import spesifik sesuai dengan yang dibutuhkan:__

  Melalui analisis kode menggunakan pmd, saya menemukan masalah adanya penggunaan import all atau import *, yaitu `import org.springframework.web.bind.annotation.*`. Hal ini menyebabkan unused import karena tidak semua kelas atau package yang diimport akan digunakan dalam kode. Setelah melihat warning dari pmd untuk masalah tersebut, saya menggantinya dengan import yang lebih spesifik agar kode menjadi lebih jelas dan terstruktur.

### 2. Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment?

Melihat konfigurasi CI/CD yang ada, saya merasa bahwa implementasinya sudah memenuhi definisi Continuous Integration (CI) dan Continuous Deployment (CD). Setiap kali ada perubahan kode yang di-push atau dibuat pull request, workflow CI otomatis dijalankan. Proses ini melakukan pengambilan kode dari repository dan menjalankan unit tests menggunakan `Gradle`, yang memastikan bahwa setiap perubahan kode diuji dan diverifikasi. Selain itu, workflow `pmd.yml` menjalankan analisis kode dengan pmd untuk mendeteksi kesalahan dan potensi masalah dalam kode Java, dan hasilnya disimpan dalam format `SARIF`. Ini membantu memastikan kualitas kode tetap terjaga sebelum deployment. Workflow `scorecard.yml` juga melakukan analisis keamanan untuk memastikan kode memenuhi best practices dalam keamanan. Dengan implementasi ini, kode yang sudah diuji dan lolos validasi dapat di-deploy secara otomatis oleh `Autodeploy` Koyeb ketika melakukan _push_ atau _pull request_, yang menurut saya memenuhi defisi dari Continuous Deployment (CD). Jadi menurut saya sendiri, kode yang dibuat sudah memenuhi definisi CI/CD.

## Code Coverage

![image](https://github.com/user-attachments/assets/5506b594-7ab3-4255-b6c4-b431d9248b90)
