QUnit.test("should not add one", function (assert) {
    assert.ok(addOneIfOdd(2) == 2);
    assert.ok(addOneIfOdd(4) == 4);
    assert.ok(addOneIfOdd(1000) == 1000);
});

QUnit.test("should add one", function (assert) {
    assert.ok(addOneIfOdd(1) == 2);
    assert.ok(addOneIfOdd(3) == 4);
    assert.ok(addOneIfOdd(1001) == 1002);
});
