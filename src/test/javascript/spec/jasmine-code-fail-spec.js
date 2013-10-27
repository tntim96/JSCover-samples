$(document).ready(function() {
    describe('Common', function() {
        it("should not add one", function() {
            expect(addOneIfOdd(2)).toEqual(2);
            expect(addOneIfOdd(4)).toEqual(4);
            expect(addOneIfOdd(1000)).toEqual(1000);
        });

        it("should add one", function() {
            expect(addOneIfOdd(1)).toEqual(2);
            expect(addOneIfOdd(3)).toEqual(4);
            expect(addOneIfOdd(1001)).toEqual(1001);
        });
    });
});
