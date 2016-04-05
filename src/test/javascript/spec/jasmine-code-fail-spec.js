describe('Common', function() {
    it("should not add one", function() {
        expect(addOneIfOdd(2)).toEqual(3);
        expect(addOneIfOdd(4)).toEqual(5);
        expect(addOneIfOdd(1000)).toEqual(1001);
    });

    it("should add one", function() {
        expect(addOneIfOdd(1)).toEqual(1);
        expect(addOneIfOdd(3)).toEqual(3);
        expect(addOneIfOdd(1001)).toEqual(1001);
    });
});
