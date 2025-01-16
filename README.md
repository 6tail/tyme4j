# Tyme [![License](https://img.shields.io/badge/license-MIT-4EB1BA.svg?style=flat-square)](https://github.com/6tail/tyme4j/blob/master/LICENSE)

Tyme是一个非常强大的日历工具库，可以看作 [Lunar](https://6tail.cn/calendar/api.html "https://6tail.cn/calendar/api.html") 的升级版，拥有更优的设计和扩展性，支持公历和农历、星座、干支、生肖、节气、法定假日等。

### Maven

```xml
<dependency>
  <groupId>cn.6tail</groupId>
  <artifactId>tyme4j</artifactId>
  <version>1.1.8</version>
</dependency>
```

## 示例

    import com.tyme.solar.SolarDay;
     
    public class Sample {
      public static void main(String[] args) {
        SolarDay solarDay = SolarDay.fromYmd(1986, 5, 29);
         
        // 1986年5月29日
        System.out.println(solarDay);
         
        // 农历丙寅年四月廿一
        System.out.println(solarDay.getLunarDay());
      }
    }

## 文档

请移步至 [https://6tail.cn/tyme.html](https://6tail.cn/tyme.html "https://6tail.cn/tyme.html")

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=6tail/tyme4j&type=Date)](https://star-history.com/#6tail/tyme4j&Date)
