$(document).ready(function() {
    test( "should not add one", function() {
        ok( addOneIfOdd(2) == 2);
        ok( addOneIfOdd(4) == 4);
        ok( addOneIfOdd(1000) == 1000);
    });

    test( "should add one", function() {
        ok( addOneIfOdd(1) == 2);
        ok( addOneIfOdd(3) == 4);
        ok( addOneIfOdd(1001) == 1002);
    });
});
