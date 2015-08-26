QUnit.test("should not add one", function (assert) {
    assert.ok(addOneIfOdd(2) == 3);
    assert.ok(addOneIfOdd(4) == 5);
    assert.ok(addOneIfOdd(1000) == 1001);
});

QUnit.test("should add one", function (assert) {
    assert.ok(addOneIfOdd(1) == 1);
    assert.ok(addOneIfOdd(3) == 3);
    assert.ok(addOneIfOdd(1001) == 1001);
});
