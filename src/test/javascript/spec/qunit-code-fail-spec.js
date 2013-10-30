$(document).ready(function() {
    test( "should not add one", function() {
        ok( addOneIfOdd(2) == 3);
        ok( addOneIfOdd(4) == 5);
        ok( addOneIfOdd(1000) == 1001);
    });

    test( "should add one", function() {
        ok( addOneIfOdd(1) == 1);
        ok( addOneIfOdd(3) == 3);
        ok( addOneIfOdd(1001) == 1001);
    });
});
