/**
 *  Demo03
 *  React 允许将代码封装成组件（component），然后像插入普通 HTML 标签一样，在网页中插入这个组件。React.createClass 方法就用于生成一个组件类
 */

var HelloStr = React.createClass({
    render:function(){
        return <h1>Hello,{this.props.name}</h1>;
    }
});

ReactDOM.render(
    <HelloStr name="wangshuang"/>,
    document.getElementById('app')
);