## Reflection 1

### Clean Code Principles
- Menggunakan nama-nama yang deskriptif untuk variabel dan method yang dibuat, seperti `updateProduct` dan `deleteProduct`.
- Setiap class memiliki fungsi masing-masing yang terpisah, contohnya `ProductController` yang menangani _request_ `HTTP`, `ProductService` menangani logika bisnis, dan `ProductRepository` menangani penyimpanan data produk.
- Membuat method kecil yang melakukan suatu hal spesifik, contohnya `getProduct` yang fungsinya adalah untuk mengembalikan produk yang sesuai berdasarkan id produk.
- Menggunakan konvensi penamaan `Java`, seperti menggunakan `camelCase` untuk nama variabel dan _method_.

### Secure Coding Practices

- Menerapkan id produk yang susah ditebak dengan memberikan id random untuk setiap produk yang dibuat menggunakan `java.util.UUID`.
- Menggunakan _method_ `HTTP` yang sesuai, seperti `GET` untuk pengambilan dan `POST` untuk operasi yang mengubah `state`.
- Redirect setelah `POST`, seperti menggunakan `redirect:/product/list` untuk menghindari duplikat submisi dari _form_.
- Melakukan sanitasi _input_, form yang dibuat menggunakan `th:field` yang dimiliki `Thymeleaf`.

### Kesalahan Yang Ditemukan
- Melanggar prinsip _clean code_ dengan tidak menggunakan _method_ yang sudah dibuat sebelumnya, _method_ `deleteProduct` membuat `productData.iterator()` baru walaupun sudah ada _method_ `findAll` yang mengembalikan `productData.iterator()`. Saya memperbaikinya dengan mengganti `productData.iterator()` di dalam `deleteProduct` dengan _method_ `findAll` yang sudah dibuat.