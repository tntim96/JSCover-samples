$(document).ready(function() {
    describe('Common', function() {
        it("should add one", function() {
            expect(addOneIfEven(2)).toEqual(3);
            expect(addOneIfEven(4)).toEqual(5);
            expect(addOneIfEven(1000)).toEqual(1001);
        });

        it("should not add one", function() {
            expect(addOneIfEven(1)).toEqual(1);
            expect(addOneIfEven(3)).toEqual(3);
            expect(addOneIfEven(1001)).toEqual(1001);
        });
    });
});
