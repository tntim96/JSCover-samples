describe('Common', function () {
  it("should not add one", function () {
    expect(addOneIfOdd(2)).to.be(2);
    expect(addOneIfOdd(4)).to.be(4);
    expect(addOneIfOdd(1000)).to.be(1000);
  });

  it("should add one", function () {
    expect(addOneIfOdd(1)).to.be(2);
    expect(addOneIfOdd(3)).to.be(4);
    expect(addOneIfOdd(1001)).to.be(1002);
  });
});
