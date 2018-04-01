/**
 *  Demo04
 *  this.props.children
 */
var aaaa = 123;
var ccc = "cba";
var CheckTitle = React.createClass({
    getDefaultProps : function () {
        return {
            title : 'Hello World'
        };
    },
    propTypes:{
        title:React.PropTypes.string.isRequired
    },
    propTypes:{
        valueHtml:React.PropTypes.string.isRequired
    },
    render:function(){
        return <h1>{this.props.title}{this.props.valueHtml}</h1>
    }
})

ReactDOM.render(
    <CheckTitle title={aaaa} valueHtml={ccc}/>,
    document.getElementById('app')
);