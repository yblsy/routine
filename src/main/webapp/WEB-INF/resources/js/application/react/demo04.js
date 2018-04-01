/**
 *  Demo04
 *  this.props.children
 */

var NoteList = React.createClass({
    render:function(){
        return <ul>
            {
                React.Children.map(this.props.children,function(chile){
                    return <li>{chile}</li>
                })
            }
        </ul>
    }
});

ReactDOM.render(
    <NoteList>
        <span>liuchen</span>
        <span>wangshuang</span>
    </NoteList>,
    document.getElementById('app')
);