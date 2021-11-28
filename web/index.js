// 轮播图js

var imgs = document.getElementById('img_list').getElementsByTagName('li'); //图片集合
var icons = document.getElementById('icon_list').getElementsByTagName('li');  //下标集合
var Left = 0;     //左移距离，初始默认为 0px（图像向左滑动）
var timer;          //设置定时器
run();

//运行函数（轮播函数）
function run() {
    //图片切换到最后时，重新归零。
    //每一张图片为 960px, 有 5 张图片，则最左移动的距离为 5*960=4800px
    if (Left <= -1500) {
        img_list.style.marginLeft = "0px";
        Left = 0;
    }
    //用n表示图片切换和停留的时间，如果刚好显示，停2000，否则以10的速度切换
    var n = (Left % 500 == 0 ? 2000 : 10);
    changeimg();
    m = Math.floor(-Left / 500);
    changeicon(m);
    Left -= 10;
    //重复执行run方法。实现一直轮播。
    timer = setTimeout(run, n);
}

//切换图片的方法
function changeimg() {

    img_list.style.marginLeft = Left + "px";
}

//切换图标的方法
function changeicon(m) {

    for (var i = 0; i < icons.length; i++) {
        //初始不设置颜色
        icons[i].style.backgroundColor = "";
    }
    //再根据m的值判断切换到第几个图标
    icons[m].style.backgroundColor = "red";
}

//切换图片

for (var i = 0; i < imgs.length; i++) {

    (function(i) {
        imgs[i].onmousemove = function() {
            clearTimeout(timer);//停止轮播
            Left = -i * 500;//使距离为完全显示图片的距离（以防在切换时放上去卡两张图片中间）
            changeimg();//使用更改的Left去切换图片
            changeicon(i);//切换对应图标
        }
        imgs[i].onmouseout = function() {
            run();
        }
    })(i);
}

//切换图标
for (var i = 0; i < icons.length; i++) {

    (function(i) {
        icons[i].onmousemove = function() {
            clearTimeout(timer);
            Left = -i * 500;
            changeimg();
            changeicon(i);
        }
        icons[i].onmouseout = function() {
            run();
        }
    })(i);
}
