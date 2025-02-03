# Gezgin Robot Projesi
**Hazırlayanlar:** Fatma Nur Kurt, Tayyib Okur
---

## Proje Açıklaması

"**Gezgin Robot Projesi**" kapsamında, belirli kurallara göre hareket eden bir robotun önündeki engelleri aşarak istenen hedefe ulaşmasını sağlayan bir oyun tasarlanması beklenmektedir. Projede iki farklı problem ele alınmaktadır; her problemde 4 adet adım bulunmaktadır.

Projede yer alan ana sınıflar:
- **Main:** Projenin başlangıç noktası. URL üzerinden verilen text dosyasına `UrlOku` metodu ile erişilir ve dosyadaki tasarıma göre ızgara, engel yapısı oluşturulur. Ayrıca rastgele başlangıç ve hedef noktaları belirlenir, tüm ızgara bulutlarla kaplanarak robotun yalnızca çevresini görmesi sağlanır. 
- **Izgara:** Dosyadan elde edilen veriler doğrultusunda ızgara yapısını oluşturur.
- **Engel:** Farklı tipteki engellerin (1, 2, 3 değerlerine sahip nesneler) ve duvarların ızgaraya yerleştirilmesinden sorumludur.
- **Robot:** Robotun hareket koordinatlarını ve izlediği yolları tutar; robotun her adımda geçtiği kareler üzerinde iz bırakması sağlanır.
- **EnKısaYolBul:** Robotun öğrendiği yollar arasında başlangıç noktasından hedefe giden en kısa yolu belirler ve ekrana çizdirir.

---


## Kullanılan JDK ve Gerekli Bağımlılıklar

Bu projeyi derlemek ve çalıştırmak için Java Development Kit (JDK) gerekmektedir.

- **Geliştirilen JDK Sürümü:** Şimdilik JDK 17 kullanılmıştır.
- **Diğer Sürümler:** JDK 11, JDK 18 veya daha güncel sürümler de büyük olasılıkla uyumlu olacaktır. Ancak projenin düzgün çalışması için JDK 17 ile test edilmiştir.
- **Bağımlılıklar:** Projede ek bir kütüphane veya bağımlılık kullanılmamaktadır. Sadece standart Java kütüphaneleri kullanılmaktadır. Eğer gelecekte harici kütüphane eklenirse, bu README bölümünde güncelleme yapılacaktır.

---

## URL'lerle İlgili Not

Projede, veri okuma işlemleri için aşağıdaki örnek URL'ler kullanılmıştır:

- `http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt`
- `http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt`

**Dikkat:** Bu URL'ler ders kapsamında örnek olarak sağlanmıştır ve güncel olmayabilir ya da erişim sağlanamayabilir. 

---

## Problem Detayları

### **PROBLEM 1**

Robotun, ızgara üzerinde verilen hedefe engellere takılmadan en kısa sürede ulaşması beklenmektedir.

- **Adım 1:** Gerekli boyutlarda karesel bir ızgara alanı oluşturulur.
- **Adım 2:**  
  - Izgara üzerine, text dosyasında verilen tasarıma göre engeller ve duvarlar yerleştirilir.  
  - Dosyada; `0` değeri engelsiz yolu, `1`, `2`, `3` değerleri ise farklı tipteki engelleri temsil etmektedir.  
    - `1` değeri: 1 karelik alan  
    - `2` değeri: Yanyana 2 kareden oluşan, maksimum 2x2’lik alan  
    - `3` değeri: Yanyana 3 kareden oluşan, maksimum 3x3’lük alan
- **Adım 3:**  
  - Robotun başlangıç ve hedef noktaları ızgara üzerinde uygun (engel veya duvar içermeyen) karelere rastgele belirlenir.  
  - `Paint` metodu kullanılarak, tüm ızgara bulutlarla kaplanır; böylece robot sadece çevresindeki kareleri görebilir.
- **Adım 4:**  
  - Robot, engel bulunmayan yollardan hareket ederek geçtiği yolları öğrenir.  
  - Bu veriler `Robot` sınıfındaki koordinat değişkenleri aracılığıyla `YolAra` metoduna aktarılır ve öğrenilen yollar bir ArrayList’te saklanır.  
  - `EnKısaYolBul` sınıfı, bu ArrayList’i kullanarak başlangıçtan hedefe giden en kısa yolu belirler ve bu yol ayrı bir ArrayList’e aktarılır.  
  - Son olarak, `Paint` metodu ile ekrana robotun izlediği yol, geçtiği adımlar ve en kısa yol çizdirilir; toplam süre (sn cinsinden) ve geçen kare sayısı ekrana yazdırılır.

---

### **PROBLEM 2**

Robotun, oluşturulan labirentteki çıkış noktasına ulaşması sağlanmalıdır.

- **Adım 1:**  
  - Kullanıcı tarafından belirlenen boyutlarda bir ızgara oluşturulur.
- **Adım 2:**  
  - Izgara üzerine, sadece `1` nolu tipte engeller yerleştirilerek bir labirent oluşturulur.  
  - Labirent içerisinde çıkışa ulaşamayan yollar da mevcuttur.  
  - Kullanıcının belirttiği boyutlar `Engel` sınıfının yapıcısına aktarılır ve `Generate` metodu ile labirent, çıkmaz sokaklar da içerecek şekilde rastgele oluşturulur.
- **Adım 3:**  
  - Labirentin giriş ve çıkış noktaları, ızgaranın çapraz köşeleri olarak belirlenir.  
  - Robot başlangıçta labirenti bilmez; yanlış bir yola girdiğinde, robotun doğru olarak tespit ettiği en son konuma dönmesi için Stack yapısı kullanılır.  
  - `LabirentYol` metodu, robotun hareket koordinat verilerini kullanarak doğru yolu arar.  
  - `Paint` metodu ile tüm labirent bulutlarla kaplanarak robotun sadece çevresini görmesi sağlanır.
- **Adım 4:**  
  - Robotun çıkışa ulaşmak için izlediği yol, adım adım ızgara üzerinde gösterilir.  
  - Her adımda, robotun geçtiği yollarda iz bırakması sağlanır.  
  - Robot hedefe ulaştığında, giriş noktasından çıkış noktasına giden yol ayrıca çizdirilir.  
  - Toplam süre (sn) ve geçen kare sayısı ekrana yazdırılır.

Son olarak, robotun geçtiği yollar ve bulduğu en kısa yol, bir text dosyasına yazdırılır.

---

## Kurulum ve Çalıştırma

1. **Depoyu Klonlayın:**
    Terminal veya Git arayüzünüz üzerinden aşağıdaki komut ile projeyi yerel makinenize klonlayın:

   ```bash
   git clone https://github.com/Dawnfairy/Gezgin-Robot.git

2. **JDK Kurulumu:**
   
    Proje, JDK 17 kullanılarak geliştirilmiş ve test edilmiştir.

    Uygun bir JDK (JDK 17 veya daha güncel) yüklü olduğundan emin olun.

    JDK'yı aşağıdaki bağlantılardan indirebilirsiniz:
    [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
    [OpenJDK 17](https://jdk.java.net/17/)
   
    JDK'yı yükledikten sonra, `JAVA_HOME` ortam değişkeninizi ayarlamayı unutmayın.


4. **Projeyi IDE'nizde Açın:**

    Tercih ettiğiniz Java geliştirme ortamında (örneğin, Eclipse, IntelliJ IDEA veya NetBeans) projeyi açın.

5. **Projeyi Çalıştırın:**

    Main.java dosyasını çalıştırarak projeyi başlatın.

---

## İletişim

Proje ile ilgili sorularınız veya katkı talepleriniz için lütfen aşağıdaki kişilerle iletişime geçin:

  Fatma Nur Kurt - kurtfatmanur8@gmail.com
  Tayyib Okur - ultratayyib@gmail.com

Bu proje, verilen gereksinimler doğrultusunda tasarlanmış ve iki farklı problem çözümünü içermektedir. Projeyi geliştirmek veya katkıda bulunmak isterseniz, lütfen pull request göndererek ya da issue oluşturarak bildirin.
