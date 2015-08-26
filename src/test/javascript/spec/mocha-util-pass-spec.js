describe('Common', function () {
  it("should add one", function () {
    expect(addOneIfEven(2)).to.be(3);
    expect(addOneIfEven(4)).to.be(5);
    expect(addOneIfEven(1000)).to.be(1001);
  });

  it("should not add one", function () {
    expect(addOneIfEven(1)).to.be(1);
    expect(addOneIfEven(3)).to.be(3);
    expect(addOneIfEven(1001)).to.be(1001);
  });
});
