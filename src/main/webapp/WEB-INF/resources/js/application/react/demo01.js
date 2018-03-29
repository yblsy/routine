ReactDOM.render(
<h1>Hello, liuchen123123!</h1>,
document.getElementById('app')
);

var names = ['Alice1', 'Emil2y', 'Kate3'];
ReactDOM.render(
    <div>
        {
            names.map(function (name) {
                return <div>Hello, {name}!</div>
            })
        }
    </div>,
    document.getElementById('app1')
);