/**
 *  Demo03
 *  React 允许将代码封装成组件（component），然后像插入普通 HTML 标签一样，在网页中插入这个组件。React.createClass 方法就用于生成一个组件类
 *  注意，组件类的第一个字母必须大写，否则会报错，比如HelloMessage不能写成helloMessage。另外，组件类只能包含一个顶层标签，否则也会报错。
 *  添加组件属性，有一个地方需要注意，就是 class 属性需要写成 className ，for 属性需要写成 htmlFor ，这是因为 class 和 for 是 JavaScript 的保留字
 */

var HelloStr = React.createClass({
    render:function(){
        return <h1>Hello,{this.props.className}</h1>;
    }
});

ReactDOM.render(
    <HelloStr className="Hi"/>,
    document.getElementById('app')
);