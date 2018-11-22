import { GroupPurchasePage } from './app.po';

describe('group-purchase App', function() {
  let page: GroupPurchasePage;

  beforeEach(() => {
    page = new GroupPurchasePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
