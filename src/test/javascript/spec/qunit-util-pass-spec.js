$(document).ready(function() {
    test( "should add one", function() {
        ok( addOneIfEven(2) == 3);
        ok( addOneIfEven(4) == 5);
        ok( addOneIfEven(1000) == 1001);
    });

    test( "should not add one", function() {
        ok( addOneIfEven(1) == 1);
        ok( addOneIfEven(3) == 3);
        ok( addOneIfEven(1001) == 1001);
    });
});
