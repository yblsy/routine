/**
 *  Demo06
 *  this.refs
 */
var MyComponent = React.createClass({
    toClick: function () {
        alert($(this.refs.myTextInput).val());
    },
    render: function () {
        return (
            <div>
                <input type="text" ref="myTextInput" />
                <input type="button" value="Focus the text input" onClick={this.toClick} />
            </div>
        )
    }
})

ReactDOM.render(
    <MyComponent />,
    document.getElementById('app')
);