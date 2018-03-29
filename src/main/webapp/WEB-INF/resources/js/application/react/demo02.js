/**
 *  Demo02
 *  配置数组，然后循环输出数组中的元素
 *  数组中的元素也可以为html
 */
var names = ['liuchen','wangshaung','liuchen01','wangshuang01'];
var htmls = [<h1>liuchen</h1>,<h2>wangshuang</h2>];

ReactDOM.render(
<div>
    {names.map(function(name){
        return <div>Hello,{name}!</div>
    })}
    {htmls}
</div>,
document.getElementById('app')
);