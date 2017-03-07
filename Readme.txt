Uygulama "frontend" ve "backend" isimli 2 klasörden oluşmaktadır.

Backend:
    *Varsayılan olarak http://localhost:8080 adresinden hizmet vermektedir.
    * In-memory database (HSQLDB) kullanılmaktadır. Uygulama kapatıldığında database'e kaydedilmiş veriler silinmektedir. 
    * "/user/login" ve "/user/register" adreslerine erişim yetkisiz kullanıcılarla sağlanabilirken, "/user/profile" adresine yalnızca yetkilendirilmiş kullanıcılar
        erişebilmektedir.
    * Oturum açma isteği onaylanan kullanıcılara backend tarafından JWT token bilgisi gönderilir.
    * JWT token bilgisini elde eden kullanıcı, yetkilendirilmiş kullanıcılara özel alanlara erişmek isterse, token bilgisini sunucuya sunmak zorundadır.
    * JWT token bilgisi http headerları içerisinde "X-Auth-Token" ismi ile sunucuya gönderilmektedir. 

Frontend
    *http://localhost:3000 adresinden ulaşılabilir.
    * /profile adresine yalnızca yetkilendirilmiş kullanıcılar erişebilmektedir.
    * Başarılı oturum açma işlemi sonucunda sunucudan gönderilen cevap içerisindeki token bilgisini localStorage'a kaydedilir.
    * Yetkilendirilmiş kullanıcılara özel alanlara erişebilmek için localStorage içerisindeki token bilgisi kontrol edilir.
    * JWT token bilgisi sunucuya gönderilir, ilgili token sunucuda validate edilir. Eğer token geçerli ise, cevap olarak kullanıcı adı ve email adresi döndürülür.
    * Oturum açmış bir kullanıcı, tekrar oturum açmak için /login adresine giriş yaparsa daha önce açtığı oturum sonlandırılır.

Uygulamayı çalıştırmak için aşağıdaki adımları takip etmeniz gerekmektedir.
    
    -Frontend:
        * node ve npm uygulamalarının sisteminizde kurulu olduğundan emin olun. (node için minimum v4.x.x, npm için minimum 3.x.x sürümleri kurulu olmalıdır)
        * Komut satırından "frontend" dosyasının root dizinine gelin ve "npm install" komutunu çalıştırın.
        * "npm install" komutu sonlandıktan sonra "npm start" komutunu çalıştırın.
    
    -Backend:
        *Backend için sisteminizde 8080 portunu kullanan aktif bir uygulama yoksa herhangi bir ayar yapmanız gerekmemektedir.

