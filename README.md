![logo](http://webmagic.io/images/logo.jpeg)

[![Build Status](https://travis-ci.org/code4craft/webmagic.png?branch=master)](https://travis-ci.org/code4craft/webmagic)

This is a forked repository from [code4craft](https://github.com/code4craft/webmagic)

It is created for scrape data from a hardware ecommerce website. 

Build a web crawler to find the best sellers products in website https://www.adafruit.com/categories and show these best sellers in a web application.

Tricks: 
The well-seller product: less than 100 units in stock, shown on the page like "70 in stock". 
The common product: more than 100 units in stock, shown on the page just "in stock".

![image1](../master/project/image1.png)

![image2](../master/project/image2.png)

Result:
![resultCapture](../master/project/resultCapture.PNG)

The implementation is [here](../master/webmagic-samples/src/main/java/us/codecraft/webmagic/samples/AdaFruitProcessor.java)

Finished result is [here](../master/project/output.txt)
