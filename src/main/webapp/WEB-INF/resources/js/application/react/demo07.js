/**
 *  Demo07
 *  this.state
 *  它的 getInitialState 方法用于定义初始状态，也就是一个对象，这个对象可以通过 this.state 属性读取。
 *  当用户点击组件，导致状态变化，this.setState 方法就修改状态值，每次修改以后，自动调用 this.render 方法，再次渲染组件
 */
var LikeButton = React.createClass({
    getInitialState : function () {
        return {liked:false,liked2:true};
    },
    handleClick:function (event) {
      this.setState({liked:!this.state.liked});
      this.setState({liked2:!this.state.liked});
    },
    render:function() {
        var text1 = this.state.liked ? 'like' : 'have\'t liked';
        var text2 = this.state.liked ? 'like2' : 'have\'t liked2';
        return (
            <p onClick={this.handleClick}>
                You {text1} this. Click to toggle.<br/>
                You {text2} this. Click to toggle.
            </p>
        )
    }
})

ReactDOM.render(
    <LikeButton />,
    document.getElementById('app')
);