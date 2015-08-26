QUnit.test("should add one", function (assert) {
    assert.ok(addOneIfEven(2) == 3);
    assert.ok(addOneIfEven(4) == 5);
    assert.ok(addOneIfEven(1000) == 1001);
});

QUnit.test("should not add one", function (assert) {
    assert.ok(addOneIfEven(1) == 1);
    assert.ok(addOneIfEven(3) == 3);
    assert.ok(addOneIfEven(1001) == 1001);
});
